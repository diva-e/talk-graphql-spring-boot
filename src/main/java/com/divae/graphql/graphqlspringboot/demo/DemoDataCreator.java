package com.divae.graphql.graphqlspringboot.demo;

import com.divae.graphql.graphqlspringboot.contract.Contract;
import com.divae.graphql.graphqlspringboot.contract.ContractHolder;
import com.divae.graphql.graphqlspringboot.person.Person;
import com.divae.graphql.graphqlspringboot.person.PersonHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class DemoDataCreator implements InitializingBean {

    private final PersonHolder personHolder;
    private final ContractHolder contractHolder;

    @Override
    public void afterPropertiesSet() throws Exception {
        Person christian = personHolder.updateOrCreatePerson(Person.builder()
                .gender(Person.Gender.MALE)
                .firstName("Christian")
                .lastName("Kumpe")
                .street("Ludwig-Erhardt-Allee").houseNumber(20)
                .zipCode("76131").city("Karlsruhe")
                .email("christian.kumpe@diva-e.com")
                .telephone("+49 721 92060 xxx")
                .sendNewsletter(true)
                .build());
        Contract rentenversicherung = contractHolder.createContract("Rentenversicherung");
        contractHolder.addContractToPerson(rentenversicherung.getId(), christian.getId());
        Contract lebensversicherung = contractHolder.createContract("Lebensversicherung");
        contractHolder.addContractToPerson(lebensversicherung.getId(), christian.getId());

        Person thorben = personHolder.updateOrCreatePerson(Person.builder()
                .gender(Person.Gender.MALE)
                .firstName("Thorben")
                .lastName("Hischke")
                .street("Ludwig-Erhardt-Allee").houseNumber(34)
                .zipCode("76131").city("Karlsruhe")
                .email("thorben.hischke@diva-e.com")
                .telephone("+49 92060 yyy")
                .sendNewsletter(false)
                .build());
        Contract haftpflichtversicherung = contractHolder.createContract("Haftpflichtversicherung");
        contractHolder.addContractToPerson(haftpflichtversicherung.getId(), thorben.getId());
    }
}
