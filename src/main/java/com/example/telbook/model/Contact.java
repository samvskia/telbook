package com.example.telbook.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Contact {

    private final UUID id;
    private final String name;
    private final String number;

    public Contact(@JsonProperty("id") UUID id,
                   @JsonProperty("name") String name,
                   @JsonProperty("number") String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }
}
