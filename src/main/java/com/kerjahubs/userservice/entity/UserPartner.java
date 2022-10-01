package com.kerjahubs.userservice.entity;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.StringToStringConverter;
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
    @Column(name = "partnerId")
    private String partnerId = DefaultValues.emptyString;
    @Column(name = "cid")
    private String cid = DefaultValues.emptyString;
    @Column(name = "partnerName")
    private String partnerName = DefaultValues.emptyString;
    @Column(name = "partnerType")
    private String partnerType = DefaultValues.emptyString;
    @Column(name = "partnerLogo")
    private String partnerLogo = DefaultValues.emptyString;
    @Column(name = "about")
    private String about = DefaultValues.emptyString;
    @Column(name = "tagline")
    private String tagline = DefaultValues.emptyString;
    @Column(name = "website")
    private String website = DefaultValues.emptyString;
    @Column(name = "sectorId")
    private String sectorId = DefaultValues.emptyString;
    @Column(name = "sectorSubId")
    private String sectorSubId = DefaultValues.emptyString;
    @Column(name = "provinceId")
    private String provinceId = DefaultValues.emptyString;
    @Column(name = "provinceName")
    private String provinceName = DefaultValues.emptyString;
    @Column(name = "districtId")
    private String districtId = DefaultValues.emptyString;
    @Column(name = "districtName")
    private String districtName = DefaultValues.emptyString;
    @Column(name = "address")
    private String address = DefaultValues.emptyString;
    @Column(name = "postalCode")
    private String postalCode = DefaultValues.emptyString;
    @Column(name = "maps")
    private String maps = DefaultValues.emptyString;
    @Column(name = "statusVerified")
    private Integer statusVerified = DefaultValues.emptyInteger;
}
