package com.rizkirm.challenge.bank.persistence.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@Data
public abstract class Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;

    @Column(name = "SECURE_ID", unique = true, length = 36)
    private String secureId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", updatable = false)
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_DATE")
    private Date modifedDate;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "DISABLED")
    private Boolean disabled;

    public Base() { }

    @PrePersist
    public void prePersist() {
        this.secureId = UUID.randomUUID().toString();
        this.disabled = false;
        this.version = 0;
    }

}