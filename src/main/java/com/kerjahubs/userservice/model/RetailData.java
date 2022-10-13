package com.kerjahubs.userservice.model;

import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.userservice.model.dto.user.DocumentDto;
import com.kerjahubs.userservice.model.dto.user.LinkedAccountsDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RetailData {
    private String fullname = DefaultValues.emptyString;
    private String gender = DefaultValues.emptyString;
    private String birthDate = DefaultValues.emptyString;
    private List<String> preferences = new ArrayList<>();
    private List<String> sectors = new ArrayList<>();
    private List<DocumentDto> documents = new ArrayList<>();
    private List<LinkedAccountsDto> linkedAccounts = new ArrayList<>();
}
