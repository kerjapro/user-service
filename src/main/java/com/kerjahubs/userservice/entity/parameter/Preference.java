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
@Table(name="PARAM_PREFERENCE")
@NoArgsConstructor
@AllArgsConstructor
public class Preference implements Serializable {
    @Id
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "id", length = 36)
    private String id = DefaultValues.emptyString;
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "value")
    private String value = DefaultValues.emptyString;
}
