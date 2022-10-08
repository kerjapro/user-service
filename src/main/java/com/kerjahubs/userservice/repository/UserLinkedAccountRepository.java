package com.kerjahubs.userservice.repository;

import com.kerjahubs.userservice.entity.UserLinkedAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLinkedAccountRepository extends JpaRepository<UserLinkedAccount, String> {
    @Query(value = "SELECT CASE WHEN COUNT(id)> 0 THEN 'true' ELSE 'false' END " +
        "FROM user_linked_account ula " +
        "WHERE lower(ula.apps) LIKE lower(:apps) AND lower(ula.apps_customer_email) like lower(:email);", nativeQuery = true)
    boolean existsOtherAccount(@Param("apps") String apps, @Param("email") String email);

    @Query(value = "SELECT * " +
        "FROM user_linked_account ula " +
        "WHERE lower(ula.apps) LIKE lower(:apps) AND lower(ula.apps_customer_email) like lower(:email);", nativeQuery = true)
    Optional<UserLinkedAccount> findByAppsAndEmail(@Param("apps") String apps, @Param("email") String email);


}
