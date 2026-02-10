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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

        Object[] result = vehicleRepo.getDcStatsLast24Hours(vehicleId);

        double totalDc = 0.0;
        double avgTemp = 0.0;

        if (result != null && result.length > 0 && result[0] instanceof Object[]) {
            Object[] row = (Object[]) result[0];

            if (row[0] != null) {
                totalDc = ((Number) row[0]).doubleValue();
            }
            if (row[1] != null) {
                avgTemp = ((Number) row[1]).doubleValue();
            }
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
