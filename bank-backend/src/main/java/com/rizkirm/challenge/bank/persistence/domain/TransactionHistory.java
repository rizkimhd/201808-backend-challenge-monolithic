package com.rizkirm.challenge.bank.persistence.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Entity
@Table(name = "TRANSACTION_HISTORY",
        indexes = {
                @Index(columnList = "sender", name = "IDX_SENDER")
        })
@DynamicUpdate
@Data
public class TransactionHistory extends Base {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SENDER")
    private UserAccount sender;

    @Column(name = "RECEIVER_ACCOUNT_NUMBER", length = 16)
    private String receiverAccount;

    @Column(name = "RECEIVER_FULL_NAME", length = 30)
    private String receiverName;

    @Column(name = "TRANSACTION_AMOUNT")
    private BigDecimal amount;

    public TransactionHistory() { }

    public TransactionHistory(UserAccount sender, String receiverAccount, String receiverName, BigDecimal amount) {
        this.sender = sender;
        this.receiverAccount = receiverAccount;
        this.receiverName = receiverName;
        this.amount = amount;
    }

}