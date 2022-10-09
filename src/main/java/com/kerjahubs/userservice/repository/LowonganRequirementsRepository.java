package com.kerjahubs.userservice.repository;

import com.kerjahubs.userservice.entity.LowonganRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LowonganRequirementsRepository extends JpaRepository<LowonganRequirement, String>{
}