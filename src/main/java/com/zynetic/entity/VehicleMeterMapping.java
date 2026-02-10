package com.zynetic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicle_meter_mapping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMeterMapping {

    @Id
    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name = "meter_id", nullable = false)
    private String meterId;
}
