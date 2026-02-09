package com.zynetic.controller;

import com.zynetic.dto.MeterTelemetryRequest;
import com.zynetic.service.MeterIngestionService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meter")
public class MeterController {

	@Autowired
	private MeterIngestionService meterService;

    @GetMapping("/ping")
    public String ping() {
        return "Meter Ingestion API is working...!!!";
    }

    @PostMapping("/ingest")
    public ResponseEntity<?> ingestMeter(@Valid @RequestBody MeterTelemetryRequest request) {

        meterService.ingest(request);
        return new ResponseEntity<>("Meter telemetry ingested successfully", HttpStatus.CREATED);
    }
}
