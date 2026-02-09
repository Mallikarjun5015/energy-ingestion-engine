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
public class MeterTelemetryRequest {

    @NotNull
    private String meterId;

    @NotNull
    private Double kwhConsumedAc;

    private Double voltage;

    @NotNull
    private LocalDateTime timestamp;
}
