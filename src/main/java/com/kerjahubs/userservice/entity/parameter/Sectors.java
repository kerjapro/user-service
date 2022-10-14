package com.kerjahubs.userservice.entity.parameter;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.StringToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "PARAM_SECTOR")
@NoArgsConstructor
@AllArgsConstructor
public class Sectors implements Serializable {
    @Id
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "id", length = 36)
    private String id = DefaultValues.emptyString;
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "value")
    private String value = DefaultValues.emptyString;
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "groupType", length = 100)
    private String groupType = DefaultValues.emptyString;
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "url", length = 100)
    private String url = DefaultValues.emptyString;
}
