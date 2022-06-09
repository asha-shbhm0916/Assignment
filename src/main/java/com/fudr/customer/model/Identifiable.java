package com.fudr.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface Identifiable {

    @JsonIgnore
    default String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
