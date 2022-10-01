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
public class UserDocument {
    @Id
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "id")
    private String id = DefaultValues.emptyString;
    @Column(name = "cid")
    private String cid = DefaultValues.emptyString;
    @Column(name = "typeId")
    private String typeId = DefaultValues.emptyString;
    @Column(name = "groupType")
    private String groupType = DefaultValues.emptyString;
    @Column(name = "value")
    private String value = DefaultValues.emptyString;
}
