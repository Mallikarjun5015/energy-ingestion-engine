package com.zynetic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zynetic.entity.VehicleTelemetryHistory;

public interface VehicleTelemetryHistoryRepository extends JpaRepository<VehicleTelemetryHistory, Long>{

}
