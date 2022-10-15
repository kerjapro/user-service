package com.kerjahubs.userservice.repository.user;

import com.kerjahubs.common.enums.PartnerType;
import com.kerjahubs.userservice.entity.user.UserPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserPartnerRepository extends JpaRepository<UserPartner, String> {
    boolean existsByPartnerName(String partnerName);

    boolean existsByCidAndPartnerType(String cid, PartnerType partnerType);

    @Query(value = "SELECT COUNT(partner_id) FROM user_partner up WHERE up.partner_type = :type ;", nativeQuery = true)
    Integer runningNumberPartner(@Param("type") String type);


}
