package com.kerjahubs.userservice.repository;

import com.kerjahubs.userservice.entity.Kelas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KelasRepository extends JpaRepository<Kelas, String> {
    @Query(value = "SELECT COUNT(id) FROM kelas WHERE kelas_type = :type ;", nativeQuery = true)
    Integer runningNumberPartner(@Param("type") String type);
}
