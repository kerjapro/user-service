package com.kerjahubs.userservice.model.request;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.userservice.model.Module;
import lombok.Data;

@Data
public class RequestAddModuleProduct {
    private String id = DefaultValues.emptyString;
    private String partnerId = DefaultValues.emptyString;
    private Module module = new Module();
}
