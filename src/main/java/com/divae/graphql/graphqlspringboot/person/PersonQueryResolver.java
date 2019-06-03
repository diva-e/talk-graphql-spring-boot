package com.divae.graphql.graphqlspringboot.person;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonQueryResolver implements GraphQLQueryResolver {

    @NonNull
    private final PersonHolder personHolder;

    @PreAuthorize("hasRole('USER')")
    public List<Person> persons() {
        return personHolder.getPersons();
    }

    @PreAuthorize("hasRole('USER')")
    public Person person(UUID id) {
        return personHolder.getPerson(id);
    }

}
