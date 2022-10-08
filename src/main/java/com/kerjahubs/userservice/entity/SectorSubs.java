package com.kerjahubs.userservice.entity;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.StringToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="PARAM_SECTOR_SUB")
@NoArgsConstructor
@AllArgsConstructor
public class SectorSubs implements Serializable {
    @Id
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "id", length = 36)
    private String id = DefaultValues.emptyString;
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "sectorGroup", length = 36)
    private String sectorGroup = DefaultValues.emptyString;
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "value")
    private String value = DefaultValues.emptyString;
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "url", length = 100)
    private String url = DefaultValues.emptyString;
}
