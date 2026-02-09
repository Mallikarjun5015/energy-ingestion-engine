package com.zynetic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePerformanceResponse {

    private String vehicleId;
    private Double totalAcConsumed;
    private Double totalDcDelivered;
    private Double efficiencyRatio;
    private Double avgBatteryTemp;
}
