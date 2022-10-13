package com.kerjahubs.userservice.entity.parameter;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.converter.StringToStringConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name="PARAM_BANNER")
@NoArgsConstructor
public class Banners implements Serializable {
    @Id
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "id", length = 36)
    private String id = DefaultValues.emptyString;
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "image", length = 8192)
    private String image = DefaultValues.emptyString;
    @Convert(converter = StringToStringConverter.class)
    @Column(name = "url", length = 100)
    private String url = DefaultValues.emptyString;
    @Column(name = "sequence", length = 5)
    private Integer sequence = DefaultValues.emptyInteger;
}
