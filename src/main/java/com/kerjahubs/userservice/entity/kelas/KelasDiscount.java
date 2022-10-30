package com.kerjahubs.userservice.entity.kelas;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.BigDecimalToBigDecimalConverter;
import com.kerjahubs.common.enums.DiscountType;
import com.kerjahubs.common.enums.ModulType;
import com.kerjahubs.common.utility.DateConversion;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "KELAS_DISCOUNT")
@NoArgsConstructor
public class KelasDiscount {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "kelasId", nullable = false, length = 36)
    private String kelasId = DefaultValues.emptyString;
    @Enumerated(EnumType.STRING)
    @Column(name = "discountType", length = 36)
    private DiscountType discountType;
    @Convert(converter = BigDecimalToBigDecimalConverter.class)
    @Column(name = "discountValue")
    private BigDecimal discountValue = BigDecimal.ZERO;
    @Column(name = "discountName", nullable = false)
    private String discountName = DefaultValues.emptyString;
    @Column(name = "discountDesc", columnDefinition = "text")
    private String discountDesc = DefaultValues.emptyString;
    @Column(name = "startDate")
    private Date startDate = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "expiredDate")
    private Date expiredDate = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "isHaveQuota", columnDefinition = "TINYINT", length = 1)
    private Boolean isHaveQuota = Boolean.TRUE;
    @Column(name = "quota", length = 5)
    private Integer quota = DefaultValues.emptyInteger;
    @Column(name = "status", columnDefinition = "TINYINT", length = 1)
    private Boolean status = Boolean.TRUE;
    @Column(name = "createdAt")
    private Date createdAt = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "updatedAt")
    private Date updatedAt = DateConversion.getDateNow(DateFormats.datetime);
}
