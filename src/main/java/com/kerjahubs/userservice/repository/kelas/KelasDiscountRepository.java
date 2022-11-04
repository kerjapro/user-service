package com.kerjahubs.userservice.repository.kelas;

import com.kerjahubs.userservice.entity.kelas.KelasDiscount;
import com.kerjahubs.userservice.entity.kelas.KelasModul;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KelasDiscountRepository extends JpaRepository<KelasDiscount, String> {
    List<KelasDiscount> findAllByKelasIdAndStatus(String kelasId, boolean status);
}
