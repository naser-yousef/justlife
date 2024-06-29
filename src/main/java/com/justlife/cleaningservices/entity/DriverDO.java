package com.justlife.cleaningservices.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "driver")
public class DriverDO implements HasId<Long> {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String mobile;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private VehicleDO vehicle;

}
