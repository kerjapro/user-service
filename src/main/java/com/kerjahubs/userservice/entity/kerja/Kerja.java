package com.kerjahubs.userservice.entity.kerja;

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
@Table(name="KERJA")
@NoArgsConstructor
public class Kerja {
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
    @Column(name = "url", length = 100)
    private String url = DefaultValues.emptyString;
    @OneToMany(mappedBy = "kerjaId")
    List<KerjaBenefit> benefits = new ArrayList<>();
    @OneToMany(mappedBy = "kerjaId")
    List<KerjaRequirement> requirements = new ArrayList<>();
}
