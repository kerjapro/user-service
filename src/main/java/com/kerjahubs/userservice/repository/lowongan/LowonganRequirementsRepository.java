package com.kerjahubs.userservice.repository.lowongan;

import com.kerjahubs.userservice.entity.lowongan.LowonganRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LowonganRequirementsRepository extends JpaRepository<LowonganRequirement, String>{
}