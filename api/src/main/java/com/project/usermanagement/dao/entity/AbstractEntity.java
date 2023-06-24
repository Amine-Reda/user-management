package com.project.usermanagement.dao.entity;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
public class AbstractEntity implements Serializable {
    private static final long serialVersionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , updatable = false, insertable = false, nullable = false)
    protected Long id;


    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedAt = LocalDateTime.now();


    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Version
    private Long version;

    public AbstractEntity(){

    }

}
