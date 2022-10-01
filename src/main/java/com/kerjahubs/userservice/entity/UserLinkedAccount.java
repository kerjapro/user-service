package com.kerjahubs.userservice.entity;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.StringToStringConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="USER_DOCUMENT")
@NoArgsConstructor
public class UserLinkedAccount {
    @Id
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "id")
    private String id = DefaultValues.emptyString;
    @Column(name = "cid")
    private String cid = DefaultValues.emptyString;
    @Column(name = "apps")
    private String apps = DefaultValues.emptyString;
    @Column(name = "appsId")
    private String appsId = DefaultValues.emptyString;
    @Column(name = "appsCustomerName")
    private String appsCustomerName = DefaultValues.emptyString;
    @Column(name = "appsCustomerImage")
    private String appsCustomerImage = DefaultValues.emptyString;
    @Column(name = "appsCustomerEmail")
    private String appsCustomerEmail = DefaultValues.emptyString;
    @Column(name = "appsToken")
    private String appsToken = DefaultValues.emptyString;
}
