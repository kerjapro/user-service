package com.kerjahubs.userservice.model;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class Preferences {
    private String preferenceId = DefaultValues.emptyString;
    private String preferenceName = DefaultValues.emptyString;
}
