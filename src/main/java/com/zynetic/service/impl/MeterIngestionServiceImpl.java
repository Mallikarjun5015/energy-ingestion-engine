package com.zynetic.service.impl;

import com.zynetic.dto.MeterTelemetryRequest;
import com.zynetic.entity.MeterTelemetryHistory;
import com.zynetic.repository.MeterLiveStatusRepository;
import com.zynetic.repository.MeterTelemetryHistoryRepository;
import com.zynetic.service.MeterIngestionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MeterIngestionServiceImpl implements MeterIngestionService {

    private final MeterTelemetryHistoryRepository historyRepository;
    private final MeterLiveStatusRepository liveStatusRepository;

    @Transactional
    public void ingest(MeterTelemetryRequest request) {

        MeterTelemetryHistory history = new MeterTelemetryHistory(
                null,
                request.getMeterId(),
                request.getKwhConsumedAc(),
                request.getVoltage(),
                request.getTimestamp()
        );
        historyRepository.save(history);

        
        liveStatusRepository.upsert(
                request.getMeterId(),
                request.getKwhConsumedAc(),
                request.getVoltage(),
                request.getTimestamp()
        );
    }
}
