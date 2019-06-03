package com.divae.graphql.graphqlspringboot.contract;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ContractHolder {

    private Map<UUID, Contract> contractStorage = new ConcurrentHashMap<>();
    private Map<UUID, UUID> contractIdToContractHolderIdStorage = new ConcurrentHashMap<>();

    public Contract getContract(UUID id) {
        return contractStorage.get(id);
    }

    public Contract createContract(@NonNull String name) {
        Contract target = Contract.builder()
                .id(UUID.randomUUID())
                .name(name)
                .build();

        contractStorage.put(target.getId(), target);

        return target;
    }

    public void addContractToPerson(@NonNull UUID contractId, @NonNull UUID personId) {
        contractIdToContractHolderIdStorage.put(contractId, personId);
    }

    public List<Contract> getContractsForPerson(UUID personId) {
        return contractIdToContractHolderIdStorage
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(personId))
                .map(entry -> getContract(entry.getKey()))
                .collect(Collectors.toList());
    }

}
