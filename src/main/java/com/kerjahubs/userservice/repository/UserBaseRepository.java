package com.kerjahubs.userservice.repository;

import com.kerjahubs.userservice.entity.UserBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBaseRepository extends JpaRepository<UserBase, String> {
}
