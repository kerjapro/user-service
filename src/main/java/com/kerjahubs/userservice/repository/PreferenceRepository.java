package com.kerjahubs.userservice.repository;

import com.kerjahubs.userservice.entity.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, String> {
}
