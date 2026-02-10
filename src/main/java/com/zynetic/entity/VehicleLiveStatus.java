package com.zynetic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_live_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleLiveStatus {

    @Id
    private String vehicleId;

    @Column(nullable = false)
    private Integer currentSoc;

    private Double lastKwhDeliveredDc;

    @Column(name = "last_battery_temp")
    private Double lastBatteryTemp;

    @Column(nullable = false)
    private LocalDateTime lastUpdatedAt;
}

