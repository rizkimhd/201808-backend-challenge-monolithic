package com.rizkirm.challenge.bank.persistence.repository;

import com.rizkirm.challenge.bank.persistence.domain.TransactionHistory;
import com.rizkirm.challenge.bank.persistence.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Repository
public interface TransactionHistoryRepository extends BaseRepository<TransactionHistory> {

    @Query("SELECT t FROM TransactionHistory t WHERE t.sender = :sender" +
            " OR (t.receiverAccount = :accountNumber AND t.transactionType ='TRANSFER')" +
            " AND t.createdDate >= :from AND t.createdDate <= :to")
    Page<TransactionHistory> findBySenderAndCreatedDateBetween(
            @Param("sender") UserAccount userAccount, @Param("accountNumber") String accountNumber,
            @Param("from") Date from, @Param("to") Date
            to,
            Pageable pageable);
}
