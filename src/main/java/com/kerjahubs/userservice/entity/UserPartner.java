package com.kerjahubs.userservice.entity;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.StringToStringConverter;
import com.kerjahubs.common.enums.PartnerType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="USER_PARTNER")
@NoArgsConstructor
public class UserPartner implements Serializable {
    @Id
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "partnerId", length = 36)
    private String partnerId = DefaultValues.emptyString;
    @Column(name = "cid", length = 36)
    private String cid = DefaultValues.emptyString;
    @Column(name = "partnerName")
    private String partnerName = DefaultValues.emptyString;
    @Enumerated(EnumType.STRING)
    @Column(name = "partnerType")
    private PartnerType partnerType;
    @Column(name = "partnerLogo", length = 8192)
    private String partnerLogo = DefaultValues.emptyString;
    @Column(name = "about", columnDefinition = "text")
    private String about = DefaultValues.emptyString;
    @Column(name = "tagline", length = 200)
    private String tagline = DefaultValues.emptyString;
    @Column(name = "website", length = 200)
    private String website = DefaultValues.emptyString;
    @Column(name = "sectorId", length = 36)
    private String sectorId = DefaultValues.emptyString;
    @Column(name = "sectorSubId", length = 36)
    private String sectorSubId = DefaultValues.emptyString;
    @Column(name = "provinceId", length = 10)
    private String provinceId = DefaultValues.emptyString;
    @Column(name = "provinceName")
    private String provinceName = DefaultValues.emptyString;
    @Column(name = "districtId", length = 10)
    private String districtId = DefaultValues.emptyString;
    @Column(name = "districtName")
    private String districtName = DefaultValues.emptyString;
    @Column(name = "address")
    private String address = DefaultValues.emptyString;
    @Column(name = "postalCode", length = 5)
    private String postalCode = DefaultValues.emptyString;
    @Column(name = "nik", length = 16)
    private String nik = DefaultValues.emptyString;
    @Column(name = "npwpNumber", length = 16)
    private String npwpNumber = DefaultValues.emptyString;
    @Column(name = "maps", length = 100)
    private String maps = DefaultValues.emptyString;
    @Column(name = "statusVerified", columnDefinition = "TINYINT", length = 1)
    private Boolean statusVerified = Boolean.FALSE;
}
