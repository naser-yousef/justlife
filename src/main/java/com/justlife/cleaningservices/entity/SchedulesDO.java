package com.justlife.cleaningservices.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedule")
public class SchedulesDO implements HasId<Long> {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date day;

    @Column(name = "start_hour")
    private Integer startHour;

    @Column(name = "end_hour")
    private Integer endHour;

    @Column
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false)
    private ProfessionalDO professional;

}
