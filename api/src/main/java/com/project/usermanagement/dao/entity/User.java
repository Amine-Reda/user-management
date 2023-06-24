package com.project.usermanagement.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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


    @OneToMany
    private List<Right> rights;

    @Column(nullable = false, columnDefinition = "int default 1")
    @Builder.Default
    protected Integer isEnabled = 1;

    public String fullName(){
        return this.firstName == null || this.lastName == null ? "" : this .firstName + " " + this.lastName;
    }

}
