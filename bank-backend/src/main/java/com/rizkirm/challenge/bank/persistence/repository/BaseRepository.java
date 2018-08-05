package com.rizkirm.challenge.bank.persistence.repository;

import com.rizkirm.challenge.bank.persistence.domain.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@NoRepositoryBean
public interface BaseRepository<T extends Base> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

    T findBySecureId(String secureId);

    T findBySecureIdAndDisabled(String secureId, Boolean disabled);

}
