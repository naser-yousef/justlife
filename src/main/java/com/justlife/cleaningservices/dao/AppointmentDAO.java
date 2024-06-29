package com.justlife.cleaningservices.dao;

import com.justlife.cleaningservices.entity.AppointmentDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentDAO extends JpaRepository<AppointmentDO, Long> {

}
