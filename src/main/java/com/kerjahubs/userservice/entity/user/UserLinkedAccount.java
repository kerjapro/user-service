package com.kerjahubs.userservice.entity.user;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.StringToStringConverter;
import com.kerjahubs.common.enums.AppsLinkedAccount;
import com.kerjahubs.common.enums.DocumentGroupType;
import com.kerjahubs.common.enums.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_LINKED_ACCOUNT")
@NoArgsConstructor
public class UserLinkedAccount {
    @Id
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "id", length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "cid", length = 36)
    private String cid = DefaultValues.emptyString;
    @Enumerated(EnumType.STRING)
    @Column(name = "apps")
    private AppsLinkedAccount apps;
    @Column(name = "appsId", length = 100)
    private String appsId = DefaultValues.emptyString;
    @Column(name = "appsCustomerName", length = 100)
    private String appsCustomerName = DefaultValues.emptyString;
    @Column(name = "appsCustomerImage", length = 8192)
    private String appsCustomerImage = DefaultValues.emptyString;
    @Column(name = "appsCustomerEmail", length = 100)
    private String appsCustomerEmail = DefaultValues.emptyString;
    @Column(name = "appsToken", length = 100)
    private String appsToken = DefaultValues.emptyString;
    @Enumerated(EnumType.STRING)
    @Column(name = "groupType", length = 30)
    private DocumentGroupType groupType;
}
