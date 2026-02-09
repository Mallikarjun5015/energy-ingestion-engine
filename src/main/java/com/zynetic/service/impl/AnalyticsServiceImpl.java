package com.zynetic.service.impl;

import com.zynetic.dto.VehiclePerformanceResponse;
import com.zynetic.repository.MeterTelemetryHistoryRepository;
import com.zynetic.repository.VehicleTelemetryHistoryRepository;
import com.zynetic.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final VehicleTelemetryHistoryRepository vehicleRepo;
    private final MeterTelemetryHistoryRepository meterRepo;

    @Override
    public VehiclePerformanceResponse getVehiclePerformance(String vehicleId) {

        Object[] dcStats = vehicleRepo.getDcStatsLast24Hours(vehicleId);
        Double totalDc = dcStats != null && dcStats[0] != null ? ((Number) dcStats[0]).doubleValue() : 0.0;
        Double avgTemp = dcStats != null && dcStats[1] != null ? ((Number) dcStats[1]).doubleValue() : 0.0;

        Double totalAc = meterRepo.getAcConsumedLast24Hours(vehicleId);
        if (totalAc == null) {
            totalAc = 0.0;
        }

        Double efficiency = (totalAc > 0) ? (totalDc / totalAc) : 0.0;

        return new VehiclePerformanceResponse(
                vehicleId,
                totalAc,
                totalDc,
                efficiency,
                avgTemp
        );
    }
}
