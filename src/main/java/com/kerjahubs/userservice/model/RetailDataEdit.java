package com.kerjahubs.userservice.model;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RetailDataEdit {
    private String fullname = DefaultValues.emptyString;
    private String gender = DefaultValues.emptyString;
    private String birthDate = DefaultValues.emptyString;
    private List<String> preferences = new ArrayList<>();
    private List<String> sectors = new ArrayList<>();
}
