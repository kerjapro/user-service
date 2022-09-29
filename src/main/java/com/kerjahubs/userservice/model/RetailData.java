package com.kerjahubs.userservice.model;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RetailData {
    private String fullname = DefaultValues.emptyString;
    private String gender = DefaultValues.emptyString;
    private String birthDate = DefaultValues.emptyString;
    private List<Preferences> preferences = new ArrayList<>();
    private List<Sectors> sectors = new ArrayList<>();
    private List<Documents> documents = new ArrayList<>();
    private List<LinkedAccounts> linkedAccounts = new ArrayList<>();
}
