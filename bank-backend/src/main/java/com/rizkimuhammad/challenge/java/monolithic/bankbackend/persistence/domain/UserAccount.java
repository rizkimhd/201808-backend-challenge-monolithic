package com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.domain;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.enums.Role;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Random;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@Table(name = "USERS")
@Entity
@DynamicUpdate
@Data
public class UserAccount extends Base {

    @Column(name = "USERNAME", length = 20, unique = true)
    private String username;

    @Column(name = "FULL_NAME", length = 30)
    private String fullName;

    @Column(name = "PASSWORD", length = 128)
    private String password;

    @Column(name = "EMAIL", length = 50, unique = true)
    private String email;

    @Column(name = "ACCOUNT_NUMBER", length = 16, unique = true)
    private String accountNumber;

    @Column(name = "ROLE", length = 5)
    private Role role;

    public UserAccount() {
    }

    public UserAccount(String username, String fullName, String password, String email) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    @PrePersist
    public void onCreate() {
        super.onCreate();

        final char[] digits = "0123456789".toCharArray();
        Random random = new Random();
        int index = digits.length;
        // Fisher-Yates.
        while (index > 1) {
            final int pos = random.nextInt(index--);
            final char tmp = digits[pos];
            digits[pos] = digits[index];
            digits[index] = tmp;
        }

        this.accountNumber = new String(digits);
        this.role = Role.ROLE_USER;
    }
}
