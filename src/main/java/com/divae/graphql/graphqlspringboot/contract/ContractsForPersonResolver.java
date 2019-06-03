package com.divae.graphql.graphqlspringboot.contract;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.divae.graphql.graphqlspringboot.person.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContractsForPersonResolver implements GraphQLResolver<Person> {

    private final ContractHolder contractHolder;

    @PreAuthorize("hasRole('USER')")
    public List<Contract> contracts(Person person) {
        return contractHolder.getContractsForPerson(person.getId());
    }

}
