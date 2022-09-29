package com.kerjahubs.userservice.model.request;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class RequestForgotPassword {
    private String email = DefaultValues.emptyString;
    private String platform = DefaultValues.emptyString;
}
