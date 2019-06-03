package com.divae.graphql.graphqlspringboot.person;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Builder
@Data
public class Person {

    private final UUID id;

    private String firstName;
    private String lastName;

    private String street;
    private int houseNumber;
    private String zipCode;
    private String city;

    private String email;
    private String telephone;

    private Instant lastModified;

}
