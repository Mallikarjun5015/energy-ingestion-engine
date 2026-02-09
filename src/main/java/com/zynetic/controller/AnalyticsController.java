package com.zynetic.controller;

import com.zynetic.dto.VehiclePerformanceResponse;
import com.zynetic.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/performance/{vehicleId}")
    public ResponseEntity<?> getVehiclePerformance(@PathVariable String vehicleId) {

        VehiclePerformanceResponse response =
                analyticsService.getVehiclePerformance(vehicleId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
