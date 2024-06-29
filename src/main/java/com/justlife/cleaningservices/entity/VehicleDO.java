package com.justlife.cleaningservices.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "vehicle")
public class VehicleDO implements HasId<Integer> {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String type;

    @OneToOne(mappedBy = "vehicle")
    private DriverDO driver;

    @OneToMany(mappedBy = "vehicle")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ProfessionalDO> professionals;

}
