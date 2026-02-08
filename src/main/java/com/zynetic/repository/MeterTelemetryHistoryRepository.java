package com.zynetic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zynetic.entity.MeterTelemetryHistory;

public interface MeterTelemetryHistoryRepository extends JpaRepository<MeterTelemetryHistory, Long>{

}
