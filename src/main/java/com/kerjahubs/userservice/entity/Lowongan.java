package com.kerjahubs.userservice.entity;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
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
@Table(name="LOWONGAN_KERJA")
@NoArgsConstructor
public class Lowongan {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "partnerId", nullable = false, length = 36)
    private String partnerId = DefaultValues.emptyString;
    @Column(name = "title")
    private String title = DefaultValues.emptyString;
    @Column(name = "description", columnDefinition = "text")
    private String description = DefaultValues.emptyString;
    @Column(name = "salaryStart", length = 36)
    private BigDecimal salaryStart = BigDecimal.ZERO;
    @Column(name = "salaryEnd", length = 36)
    private BigDecimal salaryEnd = BigDecimal.ZERO;
    @Column(name = "workingType")
    private String workingType = DefaultValues.emptyString;
    @Column(name = "postingDate")
    private Date postingDate = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "expiredDate")
    private Date expiredDate = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "companyIcon", columnDefinition = "text", length = 8192)
    private String companyIcon = DefaultValues.emptyString;
    @Column(name = "companyImage", columnDefinition = "text", length = 8192)
    private String companyImage = DefaultValues.emptyString;
    @OneToMany(mappedBy = "lowonganId")
    List<LowonganBenefit> benefits = new ArrayList<>();
    @OneToMany(mappedBy = "lowonganId")
    List<LowonganRequirement> requirements = new ArrayList<>();
}
