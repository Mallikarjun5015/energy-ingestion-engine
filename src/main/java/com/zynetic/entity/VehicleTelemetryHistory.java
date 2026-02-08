package com.zynetic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_telemetry_history",
       indexes = {
           @Index(name = "idx_vehicle_time", columnList = "vehicleId, recordedAt")
       })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTelemetryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vehicleId;

    @Column(nullable = false)
    private Integer soc;

    @Column(nullable = false)
    private Double kwhDeliveredDc;

    private Double batteryTemp;

    @Column(nullable = false)
    private LocalDateTime recordedAt;
}

