package com.zynetic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meter_telemetry_history",
       indexes = {
           @Index(name = "idx_meter_time", columnList = "meterId, recordedAt")
       })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeterTelemetryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String meterId;

    @Column(nullable = false)
    private Double kwhConsumedAc;

    private Double voltage;

    @Column(nullable = false)
    private LocalDateTime recordedAt;
}

