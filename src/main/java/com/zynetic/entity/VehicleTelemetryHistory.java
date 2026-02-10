package com.zynetic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "vehicle_telemetry_history",
    indexes = {
        @Index(name = "idx_vehicle_time", columnList = "vehicle_id, recorded_at")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTelemetryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id", nullable = false)
    private String vehicleId;

    @Column(nullable = false)
    private Integer soc;

    @Column(name = "kwh_delivered_dc", nullable = false)
    private Double kwhDeliveredDc;

    @Column(name = "battery_temp")
    private Double batteryTemp;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;
}
