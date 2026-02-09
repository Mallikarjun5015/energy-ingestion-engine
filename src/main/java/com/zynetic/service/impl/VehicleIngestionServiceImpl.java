package com.zynetic.service.impl;

import com.zynetic.dto.VehicleTelemetryRequest;
import com.zynetic.entity.VehicleTelemetryHistory;
import com.zynetic.repository.VehicleLiveStatusRepository;
import com.zynetic.repository.VehicleTelemetryHistoryRepository;
import com.zynetic.service.VehicleIngestionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VehicleIngestionServiceImpl implements VehicleIngestionService{

    private final VehicleTelemetryHistoryRepository historyRepository;
    private final VehicleLiveStatusRepository liveStatusRepository;

    @Transactional
    public void ingest(VehicleTelemetryRequest request) {

        VehicleTelemetryHistory history = new VehicleTelemetryHistory(
                null,
                request.getVehicleId(),
                request.getSoc(),
                request.getKwhDeliveredDc(),
                request.getBatteryTemp(),
                request.getTimestamp()
        );
        historyRepository.save(history);

        liveStatusRepository.upsert(
                request.getVehicleId(),
                request.getSoc(),
                request.getKwhDeliveredDc(),
                request.getBatteryTemp(),
                request.getTimestamp()
        );
    }
}

