package com.kerjahubs.userservice.model.request.user;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class RequestChangePassword {
    private String cid = DefaultValues.emptyString;
    private String newPassword = DefaultValues.emptyString;
    private String oldPassword = DefaultValues.emptyString;
    private String platform = DefaultValues.emptyString;
}
