package com.kerjahubs.userservice.repository.kerja;

import com.kerjahubs.userservice.entity.kerja.KerjaRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KerjaRequirementRepository extends JpaRepository<KerjaRequirement, String>{
}