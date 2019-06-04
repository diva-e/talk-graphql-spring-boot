package com.divae.graphql.graphqlspringboot.person;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.divae.graphql.graphqlspringboot.security.RequireUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonQueryResolver implements GraphQLQueryResolver {

    private final PersonHolder personHolder;

    @RequireUser
    public List<Person> persons() {
        return personHolder.getPersons();
    }

    @RequireUser
    public Person person(UUID personId) {
        return personHolder.getPerson(personId);
    }

}
