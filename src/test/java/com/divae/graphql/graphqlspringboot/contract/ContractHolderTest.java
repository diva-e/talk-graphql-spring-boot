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
    void createContractCarInsurance() {
        ContractCarInsurance contract = contractHolder.createContractCarInsurance("a name", "number plate");

        assertThat(contract, is(notNullValue()));
        assertThat(contract.getId(), is(notNullValue()));
        assertThat(contract.getName(), is("a name"));
        assertThat(contract.getNumberPlate(), is("number plate"));
    }

    @Test
    void getContractCarInsurance() {
        ContractCarInsurance createdContract = contractHolder.createContractCarInsurance("a name", "number plate");
        Contract loadedContract = contractHolder.getContract(createdContract.getId());

        assertThat(loadedContract, is(createdContract));
    }

    @Test
    void createContractLifeInsurance() {
        ContractLifeInsurance contract = contractHolder.createContractLifeInsurance("a name", 33);

        assertThat(contract, is(notNullValue()));
        assertThat(contract.getId(), is(notNullValue()));
        assertThat(contract.getName(), is("a name"));
        assertThat(contract.getDuration(), is(33));
    }

    @Test
    void getContractLifeInsurance() {
        Contract createdContract = contractHolder.createContractLifeInsurance("a name", 33);
        Contract loadedContract = contractHolder.getContract(createdContract.getId());

        assertThat(loadedContract, is(createdContract));
    }


    @Test
    void addAndGetContractsForPerson() {
        UUID personId = UUID.randomUUID();
        Contract contract = contractHolder.createContractLifeInsurance("a name", 33);
        UUID contractId = contract.getId();

        contractHolder.addContractToPerson(contractId, personId);
        List<Contract> contractsForPerson = contractHolder.getContractsForPerson(personId);

        assertThat(contractsForPerson, contains(contract));
    }

    @Test
    void watchContracts() {
        Contract createdContract = contractHolder.createContractLifeInsurance("a name", 33);

        Contract watchedContract = contractHolder.watchContracts().blockFirst();

        assertThat(watchedContract, is(createdContract));
    }

}