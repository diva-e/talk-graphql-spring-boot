package com.divae.graphql.graphqlspringboot.contract;

import com.divae.graphql.graphqlspringboot.person.PersonHolder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.TopicProcessor;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractHolder {

    private final Map<UUID, Contract> contractStorage = new ConcurrentHashMap<>();
    private final Map<UUID, UUID> contractIdToContractHolderIdStorage = new ConcurrentHashMap<>();
    private TopicProcessor<Contract> topic = TopicProcessor.<Contract>builder()
            .autoCancel(false)
            .bufferSize(1)
            .build();
    private final FluxSink<Contract> sink = topic.sink(FluxSink.OverflowStrategy.LATEST);
    private final PersonHolder personHolder;

    public Contract getContract(UUID id) {
        return contractStorage.get(id);
    }

    public Contract createContract(@NonNull String name) {
        Contract target = Contract.builder()
                .id(UUID.randomUUID())
                .name(name)
                .build();

        contractStorage.put(target.getId(), target);

        sink.next(target);

        return target;
    }

    public void addContractToPerson(@NonNull UUID contractId, @NonNull UUID personId) {
        contractIdToContractHolderIdStorage.put(contractId, personId);
        personHolder.notifyPersonChanged(personId);
    }

    public List<Contract> getContractsForPerson(UUID personId) {
        return contractIdToContractHolderIdStorage
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(personId))
                .map(entry -> getContract(entry.getKey()))
                .collect(Collectors.toList());
    }

    public Flux<Contract> watchContracts() {
        return topic;
    }

    public void notifyPersonChanged(UUID contractId) {
        Contract target = getContract(contractId);
        notifyPersonChanged(target);
    }

    public void notifyPersonChanged(Contract contract) {
        if (null != contract) {
            sink.next(contract);
        }
    }

}
