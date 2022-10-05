package com.kerjahubs.userservice.entity;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.NumberToNumberConverter;
import com.kerjahubs.common.utility.DateConversion;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="KELAS_BENEFIT")
@NoArgsConstructor
public class KelasBenefit {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "productId", nullable = false, length = 36)
    private String productId = DefaultValues.emptyString;
    @Column(name = "benefitName", nullable = false)
    private String benefitName = DefaultValues.emptyString;
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
