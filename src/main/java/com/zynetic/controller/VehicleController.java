package com.zynetic.controller;

import com.zynetic.dto.VehicleTelemetryRequest;
import com.zynetic.service.VehicleIngestionService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

	@Autowired
	private VehicleIngestionService vehicleService;


    @GetMapping("/ping")
    public String ping() {
        return "Vehicle Ingestion API is working...!!!";
    }

    @PostMapping("/ingest")
    public ResponseEntity<?> ingestVehicle(@Valid @RequestBody VehicleTelemetryRequest request) {

        vehicleService.ingest(request);
        return new ResponseEntity<>("Vehicle telemetry ingested successfully", HttpStatus.CREATED);
    }
}
