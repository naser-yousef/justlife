package com.justlife.cleaningservices.dao;

import com.justlife.cleaningservices.entity.ProfessionalDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProfessionalDAO extends JpaRepository<ProfessionalDO, Long> {
    List<ProfessionalDO> findBySchedulesDay(Date day);
}
