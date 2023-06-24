package com.project.usermanagement.dao.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "_right")
public class Right extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, columnDefinition = "int default 1")
    @Builder.Default
    protected Integer isEnabled = 1;

}
