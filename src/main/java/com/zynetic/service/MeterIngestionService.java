package com.zynetic.service;

import com.zynetic.dto.MeterTelemetryRequest;

public interface MeterIngestionService {

	void ingest(MeterTelemetryRequest request);
}