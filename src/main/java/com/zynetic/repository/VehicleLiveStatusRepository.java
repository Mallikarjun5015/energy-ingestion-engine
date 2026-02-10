package com.zynetic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.zynetic.entity.VehicleLiveStatus;

public interface VehicleLiveStatusRepository  extends JpaRepository<VehicleLiveStatus, String> {

    @Transactional
    @Modifying
    @Query(value = """
        INSERT INTO vehicle_live_status
        (vehicle_id, current_soc, last_kwh_delivered_dc, last_battery_temp, last_updated_at)
        VALUES (:vehicleId, :soc, :kwh, :temp, :updatedAt)
        ON DUPLICATE KEY UPDATE
        current_soc = VALUES(current_soc),
        last_kwh_delivered_dc = VALUES(last_kwh_delivered_dc),
        last_battery_temp = VALUES(last_battery_temp),
        last_updated_at = VALUES(last_updated_at)
        """, nativeQuery = true)
    void upsert(String vehicleId, Integer soc, Double kwh,
                Double temp, java.time.LocalDateTime updatedAt);
}