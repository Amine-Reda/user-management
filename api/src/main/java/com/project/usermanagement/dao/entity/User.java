package com.project.usermanagement.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "_user")
public class User extends AbstractEntity{


    @NotBlank
    @Column(nullable = false)
    private String sub;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Email
    @NotBlank
    @Column(nullable = false,unique = true)
    private String email;

    @Column
    private LocalDateTime lastConnectionAt;

    @OneToMany
    private List<Right> rights;

    @Column(nullable = false, columnDefinition = "int default 1")
    @Builder.Default
    protected Integer isEnabled = 1;

    public String fullName(){
        return this.firstName == null || this.lastName == null ? "" : this .firstName + " " + this.lastName;
    }

}
