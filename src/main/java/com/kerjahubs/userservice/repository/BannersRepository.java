package com.kerjahubs.userservice.repository;

import com.kerjahubs.userservice.entity.Banners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannersRepository extends JpaRepository<Banners, String>{
}
