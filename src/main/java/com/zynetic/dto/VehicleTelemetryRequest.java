package com.zynetic.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTelemetryRequest {

    @NotNull
    private String vehicleId;

    @NotNull
    private Integer soc;

    @NotNull
    private Double kwhDeliveredDc;

    private Double batteryTemp;

    @NotNull
    private LocalDateTime timestamp;
}
