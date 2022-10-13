package com.kerjahubs.userservice.model.request.lowongan;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class RequestLowonganRequirement {
    public String id = DefaultValues.emptyString;
    public String name = DefaultValues.emptyString;
    public Integer sequence = DefaultValues.emptyInteger;
    public String jobid = DefaultValues.emptyString;
}
