package com.kerjahubs.userservice.model.request.rating;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestRating {
    public String id = DefaultValues.emptyString;
    public String cid = DefaultValues.emptyString;
    public String productId = DefaultValues.emptyString;
    public BigDecimal rating = BigDecimal.ZERO;
    public String comment = DefaultValues.emptyString;
    public boolean status = Boolean.TRUE;
}
