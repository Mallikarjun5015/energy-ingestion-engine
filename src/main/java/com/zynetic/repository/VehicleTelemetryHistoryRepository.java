package com.zynetic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zynetic.entity.VehicleTelemetryHistory;

public interface VehicleTelemetryHistoryRepository extends JpaRepository<VehicleTelemetryHistory, Long> {

	@Query(value = """
			SELECT
			    (MAX(v.kwh_delivered_dc) - MIN(v.kwh_delivered_dc)) AS total_dc,
			    AVG(v.battery_temp) AS avg_temp
			FROM vehicle_telemetry_history v
			WHERE v.vehicle_id = :vehicleId
			  AND v.recorded_at >= NOW() - INTERVAL 24 HOUR
			""", nativeQuery = true)
	Object[] getDcStatsLast24Hours(@Param("vehicleId") String vehicleId);
}
