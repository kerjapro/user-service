package com.kerjahubs.userservice.entity;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.StringToStringConverter;
import com.kerjahubs.common.enums.Gender;
import com.kerjahubs.common.enums.UserType;
import com.kerjahubs.common.utility.DateConversion;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.commons.nullanalysis.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="USER_BASE")
@NoArgsConstructor
public class UserBase implements Serializable {
    @Id
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "cid", length = 36)
    private String cid = DefaultValues.emptyString;
    @NotNull
    @Column(name = "fullName")
    private String fullName = DefaultValues.emptyString;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Temporal(TemporalType.DATE)
    @Column(name = "birthDate")
    private Date birthDate = DateConversion.getDateNow(DateFormats.birthdate);
    @Column(name = "email", length = 100)
    private String email = DefaultValues.emptyString;
    @Column(name = "phoneNumber", length = 13)
    private String phoneNumber = DefaultValues.emptyString;
    @NotNull
    @Column(name = "password")
    private String password = DefaultValues.emptyString;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM", length = 20)
    private UserType type = UserType.RETAIL;
    @Column(name = "isVerified", columnDefinition = "TINYINT", length = 1)
    private Boolean isVerified = Boolean.FALSE;
    @Column(name = "nik", length = 16)
    private String nik = DefaultValues.emptyString;
    @Column(name = "npwpNumber", length = 16)
    private String npwpNumber = DefaultValues.emptyString;
    @Column(name = "preferences")
    private String preferences = DefaultValues.emptyString;
    @Column(name = "sectors")
    private String sectors = DefaultValues.emptyString;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt = DateConversion.getDateNow(DateFormats.datetime);
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedAt")
    private Date updatedAt = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "status", length = 1)
    private String status = DefaultValues.emptyString;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastLogin")
    private Date lastLogin = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "sessionId", length = 36)
    private String sessionId = DefaultValues.emptyString;
    @Column(name = "platformRegister", length = 20)
    private String platformRegister = DefaultValues.emptyString;
    @OneToMany(mappedBy = "cid")
    List<UserDocument> userDocuments = new ArrayList<>();
    @OneToMany(mappedBy = "cid")
    List<UserLinkedAccount> userLinkedAccounts = new ArrayList<>();
}
