package com.rizkirm.challenge.bank.persistence.repository;

import com.rizkirm.challenge.bank.persistence.domain.TransactionHistory;
import com.rizkirm.challenge.bank.persistence.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Repository
public interface TransactionHistoryRepository extends BaseRepository<TransactionHistory> {
    Page<TransactionHistory> findBySenderAndCreatedDateBetween(
            UserAccount userAccount, Date from, Date to, Pageable pageable);
}
