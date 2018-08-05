package com.rizkirm.challenge.bank.persistence.repository;

import com.rizkirm.challenge.bank.persistence.domain.UserAccount;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public interface UserAccountRepository extends BaseRepository<UserAccount> {

    UserAccount findByAccessTokenAndDisabledFalse(String token);

    UserAccount findByAccessTokenAndUsernameAndDisabledFalse(String token, String username);

    UserAccount findByUsername(String username);

    UserAccount findByUsernameOrEmail(String username, String email);

    UserAccount findByAccountNumber(String accountNumber);

    boolean existsByAccessTokenAndDisabledFalse(String token);

}