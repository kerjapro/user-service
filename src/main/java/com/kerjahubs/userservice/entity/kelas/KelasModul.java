package com.kerjahubs.userservice.entity.kelas;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.NumberToNumberConverter;
import com.kerjahubs.common.enums.KelasType;
import com.kerjahubs.common.enums.ModulType;
import com.kerjahubs.common.utility.DateConversion;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="KELAS_MODUL")
@NoArgsConstructor
public class KelasModul {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "kelasId", nullable = false, length = 36)
    private String kelasId = DefaultValues.emptyString;
    @Enumerated(EnumType.STRING)
    @Column(name = "modulType", length = 36)
    private ModulType modulType;
    @Column(name = "modulName", nullable = false)
    private String modulName = DefaultValues.emptyString;
    @Column(name = "modulDesc", columnDefinition = "text")
    private String modulDesc = DefaultValues.emptyString;
    @Column(name = "file", length = 8192)
    private String file = DefaultValues.emptyString;
    @Convert(converter = NumberToNumberConverter.class)
    @Column(name = "sequence", length = 5)
    private Integer sequence = DefaultValues.emptyInteger;
    @Column(name = "status", columnDefinition = "TINYINT", length = 1)
    private Boolean status = Boolean.TRUE;
    @Column(name = "createdAt", nullable = false, length = 36)
    private Date createdAt = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "updatedAt", nullable = false, length = 36)
    private Date updatedAt = DateConversion.getDateNow(DateFormats.datetime);
}
