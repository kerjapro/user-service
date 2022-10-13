package com.kerjahubs.userservice.model.request.user;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class RequestAddProfileDokumen {
    private String cid = DefaultValues.emptyString;
    private String groupType = DefaultValues.emptyString;
    private String type = DefaultValues.emptyString;
    private String value = DefaultValues.emptyString;
}
