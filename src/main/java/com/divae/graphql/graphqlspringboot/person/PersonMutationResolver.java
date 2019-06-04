package com.divae.graphql.graphqlspringboot.person;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.divae.graphql.graphqlspringboot.security.RequireAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonMutationResolver implements GraphQLMutationResolver {

    private final PersonHolder personHolder;

    @RequireAdmin
    public Person changePerson(Person person) {
        return personHolder.updateOrCreatePerson(person);
    }

}
