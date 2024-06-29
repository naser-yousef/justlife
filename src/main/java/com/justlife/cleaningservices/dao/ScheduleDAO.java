package com.justlife.cleaningservices.dao;

import com.justlife.cleaningservices.entity.SchedulesDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ScheduleDAO extends JpaRepository<SchedulesDO, Long> {

    List<SchedulesDO> findByDay(Date day);

}
