package com.kerjahubs.userservice.model.request.kelas;

import com.kerjahubs.common.constant.DefaultValues;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestManageDiscountProduct {
    public String id = DefaultValues.emptyString;
    public String productId = DefaultValues.emptyString;
    public String discountType = DefaultValues.emptyString;
    public BigDecimal discountValue = BigDecimal.ZERO;
    public String discountName = DefaultValues.emptyString;
    public String discountDesc = DefaultValues.emptyString;
    public String startDate = DefaultValues.emptyString;
    public String expiredDate = DefaultValues.emptyString;
    public Boolean isHaveQuota = Boolean.FALSE;
    public int quota = DefaultValues.emptyInteger;
    public Boolean status = Boolean.TRUE;
}
