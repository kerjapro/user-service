package com.kerjahubs.userservice.entity.kerja;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.utility.DateConversion;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "KERJA_BENEFIT")
@NoArgsConstructor
public class KerjaBenefit {
    @Id
    @Column(name = "id", length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "name")
    private String name = DefaultValues.emptyString;
    @Column(name = "sequence", length = 5)
    private int sequence = DefaultValues.emptyInteger;
    @Column(name = "kerjaId", length = 36)
    private String kerjaId = DefaultValues.emptyString;
    @Column(name = "status", columnDefinition = "TINYINT", length = 1)
    private Boolean status = Boolean.TRUE;
    @Column(name = "createdAt")
    private Date createdAt = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "updatedAt")
    private Date updatedAt = DateConversion.getDateNow(DateFormats.datetime);
}
