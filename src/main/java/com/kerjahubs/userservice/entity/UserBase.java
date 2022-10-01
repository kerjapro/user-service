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
@Table(name="USER_BASE")
@NoArgsConstructor
public class UserBase implements Serializable {
    @Id
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "id")
    private String cid = DefaultValues.emptyString;
    @Column(name = "fullName")
    private String fullName = DefaultValues.emptyString;
    @Column(name = "gender")
    private String gender = DefaultValues.emptyString;
    @Column(name = "birthDate")
    private Date birthDate = new Date();
    @Column(name = "email")
    private String email = DefaultValues.emptyString;
    @Column(name = "phoneNumber")
    private String phoneNumber = DefaultValues.emptyString;
    @Column(name = "password")
    private String password = DefaultValues.emptyString;
    @Column(name = "type")
    private String type = DefaultValues.emptyString;
    @Column(name = "isVerified")
    private Integer isVerified = DefaultValues.emptyInteger;
    @Column(name = "nik")
    private String nik = DefaultValues.emptyString;
    @Column(name = "npwpNumber")
    private String npwpNumber = DefaultValues.emptyString;
    @Column(name = "preferences")
    private String preferences = DefaultValues.emptyString;
    @Column(name = "sectors")
    private String sectors = DefaultValues.emptyString;
    @Column(name = "createdAt")
    private Date createdAt = new Date();
    @Column(name = "updatedAt")
    private Date updatedAt = new Date();
    @Column(name = "status")
    private String status = DefaultValues.emptyString;
    @Column(name = "lastLogin")
    private Date lastLogin = new Date();
    @Column(name = "sessionId")
    private String sessionId = DefaultValues.emptyString;
}
