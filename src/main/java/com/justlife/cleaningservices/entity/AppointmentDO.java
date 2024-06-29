package com.justlife.cleaningservices.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "appointment")
public class AppointmentDO {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @OneToMany(mappedBy = "appointment", fetch = FetchType.EAGER)
    private List<SchedulesDO> schedules;

    @Column
    private String mobile;

    @Column
    private String location;

    @Column
    private Integer status;

    @Column
    private Date createdAt;

    @PrePersist
    public void setDates() {
        createdAt = new Date();
    }
}
