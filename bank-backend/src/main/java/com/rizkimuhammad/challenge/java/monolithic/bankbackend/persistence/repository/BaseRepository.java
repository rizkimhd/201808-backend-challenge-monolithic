package com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.repository;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.persistence.domain.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by rizkimuhammad on 02/08/18.
 */
@NoRepositoryBean
public interface BaseRepository<T extends Base> extends JpaRepository<T, Integer> {

    T findBySecureIdAndDisabled(String secureId, Boolean disabled);

    T findByIdAndDisabled(Integer id, Boolean disabled);

    boolean existsById(Integer id);

}
