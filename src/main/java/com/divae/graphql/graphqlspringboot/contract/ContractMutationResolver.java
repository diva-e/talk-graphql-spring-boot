package com.divae.graphql.graphqlspringboot.contract;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractMutationResolver implements GraphQLMutationResolver {

    @NonNull
    private final ContractHolder contractHolder;

    @PreAuthorize("hasRole('ADMIN')")
    public Contract addContract(UUID personId, String name) {

        Contract contract = contractHolder.createContract(name);
        contractHolder.addContractToPerson(contract.getId(), personId);

        return contract;
    }

}
