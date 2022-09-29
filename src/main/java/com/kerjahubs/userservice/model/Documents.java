package com.kerjahubs.userservice.model;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class Documents {
    private String type = DefaultValues.emptyString;
    private String value = DefaultValues.emptyString;
}
