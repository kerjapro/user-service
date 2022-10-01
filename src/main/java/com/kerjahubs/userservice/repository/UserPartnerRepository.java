package com.kerjahubs.userservice.repository;

import com.kerjahubs.userservice.entity.UserPartner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPartnerRepository extends JpaRepository<UserPartner, String> {
    boolean existsByPartnerName(String partnerName);
}
