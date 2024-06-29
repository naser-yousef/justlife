package com.justlife.cleaningservices.service;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.justlife.cleaningservices.dao.AppointmentDAO;
import com.justlife.cleaningservices.dao.ProfessionalDAO;
import com.justlife.cleaningservices.dao.ScheduleDAO;
import com.justlife.cleaningservices.dto.AvailabilityCheck;
import com.justlife.cleaningservices.dto.AvailabilityResponse;
import com.justlife.cleaningservices.dto.BookRequest;
import com.justlife.cleaningservices.dto.BookingResponse;
import com.justlife.cleaningservices.dto.Professional;
import com.justlife.cleaningservices.entity.AppointmentDO;
import com.justlife.cleaningservices.entity.ProfessionalDO;
import com.justlife.cleaningservices.entity.SchedulesDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BookingServiceImpl extends AbstractService implements BookingService {

    @Autowired
    private ProfessionalDAO professionalDAO;

    @Autowired
    private ScheduleDAO schedulesDAO;

    @Autowired
    private AppointmentDAO appointmentDAO;

    private static final String NO_AVAILABLE_PROF_ERROR = "Cannot find # %s cleaners at the time %s";

    private static final List<Integer> WORKING_HOURS = IntStream.range(8, 22).boxed().collect(Collectors.toList());

    @Override
    public AvailabilityResponse checkAvailability(AvailabilityCheck availabilityCheck) {
        List<ProfessionalDO> professionals = professionalDAO.findBySchedulesDay(availabilityCheck.getDate());
        List<Professional> professionalList = new ArrayList<>();
        for (ProfessionalDO professionalDO : professionals) {
            List<Integer> availableHours = getAvailability(professionalDO);
            Professional professional = Professional.builder().id(professionalDO.getId()).name(professionalDO.getName())
                .availableHours(availableHours).vehicleId(professionalDO.getVehicle().getId()).build();
            professionalList.add(professional);
        }

        return new AvailabilityResponse(professionalList);
    }

    private List<Integer> getAvailability(ProfessionalDO professionalDO) {
        List<SchedulesDO> schedules = professionalDO.getSchedules();
        if (isEmpty(schedules)) {
            return WORKING_HOURS;
        }

        Set<Integer> busyHours = getBusyHours(schedules);
        return WORKING_HOURS.stream().filter(hour -> !busyHours.contains(hour)).collect(Collectors.toList());
    }

    private Set<Integer> getBusyHours(List<SchedulesDO> schedules) {
        return schedules.stream().map(schedulesDO -> getBusyHours(schedulesDO)).flatMap(Collection::stream)
            .collect(Collectors.toSet());
    }

    private List<Integer> getBusyHours(SchedulesDO schedulesDO) {
        return IntStream.rangeClosed(schedulesDO.getStartHour(), schedulesDO.getEndHour()).boxed()
            .collect(Collectors.toList());
    }

    @Override
    public BookingResponse book(BookRequest bookRequest) {
        List<Long> availableProfessionalIds = getAvailableProfessionalIds(bookRequest);
        if (availableProfessionalIds.size() < bookRequest.getNumberOfCleaners()) {
            throwValidationError(
                format(NO_AVAILABLE_PROF_ERROR, bookRequest.getNumberOfCleaners(),
                       bookRequest.getStartDate()));
        }

        List<ProfessionalDO> professionals = professionalDAO.findAllById(availableProfessionalIds);
        List<SchedulesDO> schedules = professionals.stream()
            .map(professionalDO -> createSchedule(bookRequest, professionalDO))
            .collect(Collectors.toList());
        professionalDAO.saveAll(professionals);
        AppointmentDO appointment = createAppointment(schedules);
        AppointmentDO savedAppoint = appointmentDAO.save(appointment);

        return BookingResponse.builder().professionalIds(extractIds(professionals)).appointmentId(savedAppoint.getId())
            .build();
    }

    private AppointmentDO createAppointment(List<SchedulesDO> schedules) {
        return AppointmentDO.builder().clientName("Ahmad").location("Jordan").mobile("0787879888")
            .status(AppointmentStatus.PENDING.getStatusId()).schedules(schedules)
            .build();
    }

    private List<Long> getAvailableProfessionalIds(BookRequest bookRequest) {
        AvailabilityCheck availabilityCheck = new AvailabilityCheck();
        availabilityCheck.setDate(bookRequest.getStartDate());
        List<Professional> professionals = checkAvailability(availabilityCheck).getProfessionals();
        Map<Integer, List<Professional>> vehiclesProfessionalsIds = groupProfessionalsByVehicles(professionals);
        return fetchProfessionalsBelongsToSameVehicle(vehiclesProfessionalsIds, bookRequest.getNumberOfCleaners());
    }

    private List<Long> fetchProfessionalsBelongsToSameVehicle(Map<Integer, List<Professional>> vehiclesProfessionalsIds,
                                                              Integer numberOfCleaners) {
        return vehiclesProfessionalsIds.entrySet().stream()
            .filter(entry -> entry.getValue().size() >= numberOfCleaners)
            .map(Map.Entry::getValue)
            .findFirst()
            .map(this::extractIds)
            .orElse(Collections.emptyList()).stream().limit(numberOfCleaners).collect(Collectors.toList());
    }

    private Map<Integer, List<Professional>> groupProfessionalsByVehicles(List<Professional> professionals) {
        return professionals.stream().collect(Collectors.groupingBy(Professional::getVehicleId, Collectors.toList()));
    }

    private SchedulesDO createSchedule(BookRequest request, ProfessionalDO professional) {
        int hour = extractHour(request.getStartDate());
        SchedulesDO schedulesDO = SchedulesDO.builder()
            .professional(professional)
            .day(request.getStartDate())
            .startHour(hour).endHour(hour + request.getDuration()).build();
        if (isNotEmpty(professional.getSchedules())) {
            professional.getSchedules().add(schedulesDO);
        } else {
            professional.setSchedules(singletonList(schedulesDO));
        }

        return schedulesDO;
    }

    private int extractHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public BookingResponse update(BookRequest bookRequest, Long id) {
        return null;
    }
}
