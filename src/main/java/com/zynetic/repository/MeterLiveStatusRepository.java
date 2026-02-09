package com.zynetic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.zynetic.entity.MeterLiveStatus;


public interface MeterLiveStatusRepository extends JpaRepository<MeterLiveStatus, String>{

	 @Transactional
	    @Modifying
	    @Query(value = """
	        INSERT INTO meter_live_status
	        (meter_id, last_kwh_consumed_ac, last_voltage, last_updated_at)
	        VALUES (:meterId, :kwh, :voltage, :updatedAt)
	        ON DUPLICATE KEY UPDATE
	        last_kwh_consumed_ac = VALUES(last_kwh_consumed_ac),
	        last_voltage = VALUES(last_voltage),
	        last_updated_at = VALUES(last_updated_at)
	        """, nativeQuery = true)
	    void upsert(String meterId, Double kwh, Double voltage, java.time.LocalDateTime updatedAt);

}
