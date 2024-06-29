package com.justlife.cleaningservices.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Data
@Entity
@Table(name = "professional")
public class ProfessionalDO implements HasId<Long> {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String mobile;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private VehicleDO vehicle;

    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<SchedulesDO> schedules;

}
