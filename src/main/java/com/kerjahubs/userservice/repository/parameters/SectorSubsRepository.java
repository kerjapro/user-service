package com.kerjahubs.userservice.repository.parameters;

import com.kerjahubs.userservice.entity.parameter.SectorSubs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectorSubsRepository extends JpaRepository<SectorSubs, String> {
    List<SectorSubs> findBySectorGroupOrderByValueAsc(String id);
}
