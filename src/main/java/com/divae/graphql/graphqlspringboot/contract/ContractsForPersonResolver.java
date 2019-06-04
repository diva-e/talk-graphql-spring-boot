package com.divae.graphql.graphqlspringboot.contract;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.divae.graphql.graphqlspringboot.person.Person;
import com.divae.graphql.graphqlspringboot.security.RequireUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractsForPersonResolver implements GraphQLResolver<Person> {

    private final ContractHolder contractHolder;

    @RequireUser
    public List<Contract> contracts(Person person) {
        return contractHolder.getContractsForPerson(person.getId());
    }

}
