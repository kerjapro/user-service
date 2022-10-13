package com.kerjahubs.userservice.repository.lowongan;

import com.kerjahubs.userservice.entity.lowongan.Lowongan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LowonganRepository extends JpaRepository<Lowongan, String> {
}
