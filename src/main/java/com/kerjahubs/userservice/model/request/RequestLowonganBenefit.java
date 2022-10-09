package com.kerjahubs.userservice.model.request;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class RequestLowonganBenefit {
    public String id = DefaultValues.emptyString;
    public String name = DefaultValues.emptyString;
    public Integer sequence = DefaultValues.emptyInteger;
    public String jobid = DefaultValues.emptyString;
}
