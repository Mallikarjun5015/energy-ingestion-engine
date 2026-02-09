package com.zynetic.service;

import com.zynetic.dto.VehicleTelemetryRequest;

public interface VehicleIngestionService {

	 void ingest(VehicleTelemetryRequest request);
}
