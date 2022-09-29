package com.kerjahubs.userservice.model;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class OtherAccount {
    private String type = DefaultValues.emptyString;
    private String id = DefaultValues.emptyString;
    private String name = DefaultValues.emptyString;
    private String image = DefaultValues.emptyString;
    private String email = DefaultValues.emptyString;
    private String token = DefaultValues.emptyString;
}
