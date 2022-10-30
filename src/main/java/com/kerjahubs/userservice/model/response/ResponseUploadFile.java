package com.kerjahubs.userservice.model.response;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class ResponseUploadFile {
    public String urlFile = DefaultValues.emptyString;
}
