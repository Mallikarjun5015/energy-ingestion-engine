package com.zynetic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zynetic.entity.MeterTelemetryHistory;

public interface MeterTelemetryHistoryRepository extends JpaRepository<MeterTelemetryHistory, Long> {

	@Query(value = """
			SELECT
			    (MAX(m.kwh_consumed_ac) - MIN(m.kwh_consumed_ac))
			FROM meter_telemetry_history m
			WHERE m.meter_id = :meterId
			  AND m.recorded_at >= NOW() - INTERVAL 24 HOUR
			""", nativeQuery = true)
	Double getAcConsumedLast24Hours(@Param("meterId") String meterId);
}
