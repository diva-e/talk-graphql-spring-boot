package com.divae.graphql.graphqlspringboot.contract;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.divae.graphql.graphqlspringboot.security.RequireAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractMutationResolver implements GraphQLMutationResolver {

    private final ContractHolder contractHolder;

    @RequireAdmin
    public Contract addContractToPerson(UUID personId, String name) {
        Contract contract = contractHolder.createContract(name);
        contractHolder.addContractToPerson(contract.getId(), personId);
        return contract;
    }

}
