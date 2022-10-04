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

    @Query(value = "SELECT ub.cid, ub.email, ub.phone_number, ub.type, ub.is_verified, ub.last_login, ub.full_name, ub.gender, ub.birth_date, ub.preferences, ub.sectors" +
        "FROM user_base ub" +
        "WHERE ub.password = :password " +
        "AND ub.email = :username " +
        "OR ub.phone_number = :username ;", nativeQuery = true)
    Optional<UserBase> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


}
