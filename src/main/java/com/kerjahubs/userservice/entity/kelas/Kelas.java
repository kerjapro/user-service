package com.kerjahubs.userservice.entity.kelas;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.BigDecimalToBigDecimalConverter;
import com.kerjahubs.common.converter.NumberToNumberConverter;
import com.kerjahubs.common.enums.KelasType;
import com.kerjahubs.common.utility.DateConversion;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "KELAS")
@NoArgsConstructor
public class Kelas {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "partnerId", nullable = false, length = 36)
    private String partnerId = DefaultValues.emptyString;
    @Column(name = "sectorId", nullable = false, length = 36)
    private String sectorId = DefaultValues.emptyString;
    @Column(name = "sectorSubId", nullable = false, length = 36)
    private String sectorSubId = DefaultValues.emptyString;
    @Enumerated(EnumType.STRING)
    @Column(name = "kelasType", length = 36)
    private KelasType kelasType;
    @Column(name = "kelasName", nullable = false, length = 100)
    private String kelasName = DefaultValues.emptyString;
    @Column(name = "kelasDesc", columnDefinition = "text")
    private String kelasDesc = DefaultValues.emptyString;
    @Convert(converter = BigDecimalToBigDecimalConverter.class)
    @Column(name = "price")
    private BigDecimal price = BigDecimal.ZERO;
    @Convert(converter = NumberToNumberConverter.class)
    @Column(name = "seat", length = 5)
    private Integer seat = DefaultValues.emptyInteger;
    @Column(name = "url", nullable = false, length = 100)
    private String url = DefaultValues.emptyString;
    @Column(name = "image", length = 8192)
    private String image = DefaultValues.emptyString;
    @Column(name = "language", length = 36)
    private String language = DefaultValues.emptyString;
    @Column(name = "eventDate")
    private Date eventDate = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "expiredDate")
    private Date expiredDate = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "createdAt")
    private Date createdAt = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "updatedAt")
    private Date updatedAt = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "status", columnDefinition = "TINYINT", length = 1)
    private Boolean status = Boolean.TRUE;
    @Column(name = "views", length = 10)
    private int views = DefaultValues.emptyInteger;
    @OneToMany(mappedBy = "kelasId")
    List<KelasBenefit> benefits = new ArrayList<>();
    @OneToMany(mappedBy = "kelasId")
    List<KelasModul> moduls = new ArrayList<>();
}
