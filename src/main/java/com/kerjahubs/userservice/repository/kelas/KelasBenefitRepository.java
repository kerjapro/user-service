package com.kerjahubs.userservice.repository.kelas;

import com.kerjahubs.userservice.entity.kelas.KelasBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface KelasBenefitRepository extends JpaRepository<KelasBenefit, String> {
    @Query(value = "SELECT kb.id, kb.product_id, kb.benefit_name, kb.sequence, kb.status, kb.created_at, kb.updated_at" +
        "FROM kelas_benefit kb" +
        "WHERE kb.product_id = :productId ;", nativeQuery = true)
    Optional<KelasBenefit> findByProductId(@Param("productId") String productId);
}
