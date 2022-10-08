package com.kerjahubs.userservice.repository;

import com.kerjahubs.userservice.entity.UserBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBaseRepository extends JpaRepository<UserBase, String> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPassword(String password);

    @Query(value = "SELECT *" +
        " FROM user_base ub" +
        " WHERE ub.password = :password" +
        " AND ub.email = :username" +
        " OR ub.phone_number = :username ;", nativeQuery = true)
    Optional<UserBase> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT *" +
        " FROM user_base ub" +
        " WHERE ub.email = :email ;", nativeQuery = true)
    Optional<UserBase> findByEmail(@Param("email") String email);

    @Query(value = "SELECT *" +
        " FROM user_base ub" +
        " WHERE ub.cid = :cid" +
        " AND ub.password = :password ;", nativeQuery = true)
    Optional<UserBase> findByIdAndPassword(@Param("cid") String cid, @Param("password") String password);
}
