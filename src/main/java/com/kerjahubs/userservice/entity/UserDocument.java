package com.kerjahubs.userservice.entity;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.StringToStringConverter;
import com.kerjahubs.common.enums.DocumentGroupType;
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
    @Column(name = "id", length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "cid", length = 36)
    private String cid = DefaultValues.emptyString;
    @Column(name = "typeId", length = 36)
    private String typeId = DefaultValues.emptyString;
    @Enumerated(EnumType.STRING)
    @Column(name = "groupType")
    private DocumentGroupType groupType;
    @Column(name = "value")
    private String value = DefaultValues.emptyString;
}
