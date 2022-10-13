package com.kerjahubs.userservice.repository.parameters;

import com.kerjahubs.userservice.entity.parameter.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, String> {
}
