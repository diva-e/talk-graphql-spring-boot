package com.divae.graphql.graphqlspringboot.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractLifeInsurance implements Contract {
    UUID id;
    String name;
    Integer duration;
}
