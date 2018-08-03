package com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.repository;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.domain.UserAccount;
import org.springframework.stereotype.Repository;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@Repository
public interface UserAccountRepository extends BaseRepository<UserAccount> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    UserAccount findByUsername(String username);
}
