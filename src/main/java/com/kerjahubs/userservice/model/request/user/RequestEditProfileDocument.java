package com.kerjahubs.userservice.model.request.user;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class RequestEditProfileDocument {
    private String id = DefaultValues.emptyString;
    private String value = DefaultValues.emptyString;
}
