package com.kerjahubs.userservice.repository;

import com.kerjahubs.userservice.entity.SectorSubs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectorSubsRepository extends JpaRepository<SectorSubs, String> {
    List<SectorSubs> findBySectorGroupOrderByValueAsc(String id);
}
