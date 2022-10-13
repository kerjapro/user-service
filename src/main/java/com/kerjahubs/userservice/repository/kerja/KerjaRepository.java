package com.kerjahubs.userservice.repository.kerja;

import com.kerjahubs.userservice.entity.kerja.Kerja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KerjaRepository extends JpaRepository<Kerja, String> {
}
