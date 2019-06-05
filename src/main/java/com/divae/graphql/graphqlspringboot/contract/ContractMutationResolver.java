package com.divae.graphql.graphqlspringboot.contract;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.divae.graphql.graphqlspringboot.security.RequireAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractMutationResolver implements GraphQLMutationResolver {

    private final ContractHolder contractHolder;

    @RequireAdmin
    @Deprecated
    public Contract addContractToPerson(UUID personId, String name) {
        Contract contract = contractHolder.createContract(name);
        contractHolder.addContractToPerson(contract.getId(), personId);
        return contract;
    }

    @RequireAdmin
    public ContractLifeInsurance createContractLifeInsurance(String name, Integer duration) {
        return  contractHolder.createContractLifeInsurance(name, duration);
    }

    @RequireAdmin
    public ContractCarInsurance createContractCarInsurance(String name, String numberPlate) {
        return  contractHolder.createContractCarInsurance(name, numberPlate);
    }

    @RequireAdmin
    Boolean assignContractToPerson(UUID personId, UUID contractId) {
        return contractHolder.addContractToPerson(contractId, personId);
    }
}
