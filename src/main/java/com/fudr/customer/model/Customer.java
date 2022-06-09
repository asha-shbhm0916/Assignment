package com.fudr.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Objects;

@Data
public class Customer implements Identifiable {
    final String customerId;
    String name;
    String email;
    String phoneNumber;
    Integer age;

    private Customer(String name) {
        this.customerId = getId();
        this.name = name;
    }

    public static Customer createCustomer(String name) {
        return new Customer(name);
    }

    @JsonIgnore
    Boolean isKycUpdated() {
        return Objects.nonNull(email) && Objects.nonNull(phoneNumber) && Objects.nonNull(age);
    }
}
