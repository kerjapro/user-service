package com.kerjahubs.userservice.model.request;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.userservice.model.Benefit;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestAddBenefitProduct {
    private String id = DefaultValues.emptyString;
    private String partnerId = DefaultValues.emptyString;
    private List<Benefit> benefits = new ArrayList<>();
}
