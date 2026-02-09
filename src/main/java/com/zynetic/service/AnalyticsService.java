package com.zynetic.service;

import com.zynetic.dto.VehiclePerformanceResponse;

public interface AnalyticsService {
    VehiclePerformanceResponse getVehiclePerformance(String vehicleId);
}