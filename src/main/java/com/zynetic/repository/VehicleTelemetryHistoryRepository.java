package com.zynetic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zynetic.entity.VehicleTelemetryHistory;

public interface VehicleTelemetryHistoryRepository extends JpaRepository<VehicleTelemetryHistory, Long>{

	@Query(value = """
		    SELECT 
		        SUM(v.kwh_delivered_dc),
		        AVG(v.battery_temp)
		    FROM vehicle_telemetry_history v
		    WHERE v.vehicle_id = :vehicleId
		      AND v.recorded_at >= NOW() - INTERVAL 24 HOUR
		    """, nativeQuery = true)
		Object[] getDcStatsLast24Hours(@Param("vehicleId") String vehicleId);

}
