package com.divae.graphql.graphqlspringboot.contract;

import com.divae.graphql.graphqlspringboot.person.PersonHolder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class ContractHolderTest {

    @Mock
    PersonHolder personHolder;

    @InjectMocks
    ContractHolder contractHolder;

    @Test
    void createContract() {
        Contract contract = contractHolder.createContract("a name");

        assertThat(contract, is(notNullValue()));
        assertThat(contract.getId(), is(notNullValue()));
        assertThat(contract.getName(), is("a name"));
    }

    @Test
    void getContract() {
        Contract createdContract = contractHolder.createContract("a name");
        Contract loadedContract = contractHolder.getContract(createdContract.getId());

        assertThat(loadedContract, is(createdContract));
    }


    @Test
    void addAndGetContractsForPerson() {
        UUID personId = UUID.randomUUID();
        Contract contract = contractHolder.createContract("a name");
        UUID contractId = contract.getId();

        contractHolder.addContractToPerson(contractId, personId);
        List<Contract> contractsForPerson = contractHolder.getContractsForPerson(personId);

        assertThat(contractsForPerson, contains(contract));
    }

    @Test
    void watchContracts() {
        Contract createdContract = contractHolder.createContract("a name");

        Contract watchedContract = contractHolder.watchContracts().blockFirst();

        assertThat(watchedContract, is(createdContract));
    }

}