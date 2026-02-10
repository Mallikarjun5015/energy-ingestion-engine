package com.zynetic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meter_live_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeterLiveStatus {

	@Column(name = "meter_id", unique = true)
    @Id
    private String meterId;

    @Column(nullable = false)
    private Double lastKwhConsumedAc;

    private Double lastVoltage;

    @Column(nullable = false)
    private LocalDateTime lastUpdatedAt;
}

