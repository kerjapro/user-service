package com.kerjahubs.userservice.entity.rating;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.utility.DateConversion;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "RATING_KELAS")
@NoArgsConstructor
public class Rating {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "cid", nullable = false, length = 36)
    private String cid = DefaultValues.emptyString;
    @Column(name = "kelasId", nullable = false, length = 36)
    private String kelasId = DefaultValues.emptyString;
    @Column(name = "rating")
    private BigDecimal rating = BigDecimal.ZERO;
    @Column(name = "comment")
    private String comment = DefaultValues.emptyString;
    @Column(name = "createdAt")
    private Date createdAt = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "updatedAt")
    private Date updatedAt = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "status", columnDefinition = "TINYINT", length = 1)
    private Boolean status = Boolean.TRUE;
}
