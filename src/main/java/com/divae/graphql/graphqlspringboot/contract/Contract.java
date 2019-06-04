package com.divae.graphql.graphqlspringboot.contract;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Contract {

    private final UUID id;

    private String name;

}
