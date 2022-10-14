package com.kerjahubs.userservice.entity.user;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.StringToStringConverter;
import com.kerjahubs.common.enums.DocumentGroupType;
import com.kerjahubs.userservice.entity.parameter.DocumentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_DOCUMENT")
@SecondaryTable(name = "DOCUMENT_TYPE", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@NoArgsConstructor
public class UserDocument {
    @Id
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "id", length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "cid", length = 36)
    private String cid = DefaultValues.emptyString;
    @Enumerated(EnumType.STRING)
    @Column(name = "groupType", length = 30)
    private DocumentGroupType groupType;
    @Lob
    @Column(name = "value", columnDefinition = "TEXT", length = 8192)
    private String value = DefaultValues.emptyString;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "typeId")
    private DocumentType documentType;
}
