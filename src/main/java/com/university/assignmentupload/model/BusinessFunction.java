package com.university.assignmentupload.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "business_function")
public class BusinessFunction {

    @Id
    @Column(name = "BUSINESS_FUNCTION_ID")
    private String businessFunctionId;

    @Column(name = "BUSINESS_FUNCTION_DESC")
    private String businessFunctionDesc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BusinessFunction that = (BusinessFunction) o;
        return businessFunctionId != null && Objects.equals(businessFunctionId, that.businessFunctionId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
