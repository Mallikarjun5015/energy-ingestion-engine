package com.zynetic.service.impl;

import com.zynetic.dto.VehiclePerformanceResponse;
import com.zynetic.entity.VehicleMeterMapping;
import com.zynetic.repository.MeterTelemetryHistoryRepository;
import com.zynetic.repository.VehicleMeterMappingRepository;
import com.zynetic.repository.VehicleTelemetryHistoryRepository;
import com.zynetic.service.AnalyticsService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final VehicleTelemetryHistoryRepository vehicleRepo;
    private final MeterTelemetryHistoryRepository meterRepo;
    private final VehicleMeterMappingRepository mappingRepo;

    @Override
    public VehiclePerformanceResponse getVehiclePerformance(String vehicleId) {

        VehicleMeterMapping mapping = mappingRepo.findById(vehicleId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Vehicle not mapped to any meter"
                        ));

        String meterId = mapping.getMeterId();

        Object[] dcStats = vehicleRepo.getDcStatsLast24Hours(vehicleId);

        double totalDc = 0.0;
        double avgTemp = 0.0;

        if (dcStats != null && dcStats.length == 2) {
            if (dcStats[0] != null) totalDc = ((Number) dcStats[0]).doubleValue();
            if (dcStats[1] != null) avgTemp = ((Number) dcStats[1]).doubleValue();
        }

        Double acVal = meterRepo.getAcConsumedLast24Hours(meterId);
        double totalAc = acVal != null ? acVal : 0.0;

        double efficiency = totalAc > 0 ? totalDc / totalAc : 0.0;

        return new VehiclePerformanceResponse(
                vehicleId,
                totalAc,
                totalDc,
                efficiency,
                avgTemp
        );
    
    }
}
