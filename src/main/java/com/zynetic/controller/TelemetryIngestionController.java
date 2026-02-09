package com.zynetic.controller;

import com.zynetic.dto.MeterTelemetryRequest;
import com.zynetic.dto.VehicleTelemetryRequest;
import com.zynetic.service.impl.MeterIngestionServiceImpl;
import com.zynetic.service.impl.VehicleIngestionServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/ingest")
@RequiredArgsConstructor
public class TelemetryIngestionController {

    private final MeterIngestionServiceImpl meterService;
    private final VehicleIngestionServiceImpl vehicleService;

    @PostMapping("/meter")
    public ResponseEntity<Void> ingestMeter(
            @Valid @RequestBody MeterTelemetryRequest request) {

        meterService.ingest(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/vehicle")
    public ResponseEntity<Void> ingestVehicle(
            @Valid @RequestBody VehicleTelemetryRequest request) {

        vehicleService.ingest(request);
        return ResponseEntity.accepted().build();
    }
}
