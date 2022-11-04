package com.kerjahubs.userservice.repository.transaction;

import com.kerjahubs.common.enums.TransactionStatus;
import com.kerjahubs.userservice.entity.transaction.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, String> {
    List<TransactionItem> findAllByDiscountIdAndTransactionStatusIn (String discountId, List<TransactionStatus> transactionStatus);
}