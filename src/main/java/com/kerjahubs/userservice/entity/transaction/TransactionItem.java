package com.kerjahubs.userservice.entity.transaction;

import com.kerjahubs.common.constant.DateFormats;
import com.kerjahubs.common.constant.DefaultValues;
import com.kerjahubs.common.enums.TransactionStatus;
import com.kerjahubs.common.utility.DateConversion;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "TRANSACTION_ITEM")
@NoArgsConstructor
public class TransactionItem {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id = DefaultValues.emptyString;
    @Column(name = "transactionId", nullable = false, length = 36)
    private String transactionId = DefaultValues.emptyString;
    @Column(name = "discountId", nullable = false, length = 36)
    private String discountId = DefaultValues.emptyString;
    @Column(name = "kelasId", nullable = false, length = 36)
    private String kelasId = DefaultValues.emptyString;
    @Column(name = "total", length = 10)
    private int total = DefaultValues.emptyInteger;
    @Column(name = "totalAmount")
    private BigDecimal totalAmount = BigDecimal.ZERO;
    @Column(name = "totalDiscount")
    private BigDecimal totalDiscount = BigDecimal.ZERO;
    @Column(name = "totalFee")
    private BigDecimal totalFee = BigDecimal.ZERO;;
    @Column(name = "totalPayment")
    private BigDecimal totalPayment = BigDecimal.ZERO;;
    @Enumerated(EnumType.STRING)
    @Column(name = "transactionStatus", length = 36)
    private TransactionStatus transactionStatus;
    @Column(name = "createdAt")
    private Date createdAt = DateConversion.getDateNow(DateFormats.datetime);
    @Column(name = "updatedAt")
    private Date updatedAt = DateConversion.getDateNow(DateFormats.datetime);
}
