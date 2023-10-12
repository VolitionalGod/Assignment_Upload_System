package com.university.assignmentupload.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "ROLE_DESC")
    private String roleDesc;

/*    @ManyToMany
    @JoinTable(
            name = "role_business_function",
            joinColumns = @JoinColumn(
                    name = "ROLE", referencedColumnName = "ROLE"),
            inverseJoinColumns = @JoinColumn(
                    name = "BUSINESS_FUNCTION", referencedColumnName = "BUSINESS_FUNCTION")
    )
    private Collection<BusinessFunction> businessFunctions;
*/


}
