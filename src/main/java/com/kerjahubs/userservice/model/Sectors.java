package com.kerjahubs.userservice.model;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

@Data
public class Sectors {
    private String sectorId = DefaultValues.emptyString;
    private String sectorName = DefaultValues.emptyString;
}
