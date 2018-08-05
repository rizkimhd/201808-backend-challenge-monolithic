package com.rizkirm.challenge.bank.persistence.domain;

import com.rizkirm.challenge.bank.util.GeneratorUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@Entity
@Table(name = "USER_ACCOUNT",
        indexes = {
                @Index(columnList = "USERNAME", name = "IDX_USERNAME", unique = true)
        })
@DynamicUpdate
@Data
public class UserAccount extends Base {

    @Column(name = "PASSWORD", length = 128, nullable = false)
    private String password;

    @Column(name = "USERNAME", length = 20, nullable = false)
    private String username;

    @Column(name = "FULL_NAME", length = 30, nullable = false)
    private String fullName;

    @Column(name = "EMAIL", length = 50, nullable = false)
    private String email;

    @Column(name = "ACCOUNT_NUMBER", length = 10, unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    @Column(name = "ACCESS_TOKEN", length = 36)
    private String accessToken;

    public UserAccount() {
    }

    public UserAccount(String username, String fullName, String password, String email, BigDecimal balance) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.balance = balance == null ? BigDecimal.ZERO : balance;
    }

    @PrePersist
    public void prePersist() {
        super.prePersist();
        this.accountNumber = new GeneratorUtil().generate10RandomDigits();
    }

}