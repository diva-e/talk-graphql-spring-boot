package com.divae.graphql.graphqlspringboot.person;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonMutationResolver implements GraphQLMutationResolver {

    @NonNull
    private final PersonHolder personHolder;

    @PreAuthorize("hasRole('ADMIN')")
    public Person changePerson(Person input) {
        return personHolder.updateOrCreatePerson(input);
    }

}
