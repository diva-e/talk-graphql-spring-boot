package com.divae.graphql.graphqlspringboot.person;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonSubscriptionResolver implements GraphQLSubscriptionResolver {

    private final PersonHolder personHolder;

    public Publisher<Person> watchPersons() {
        return personHolder.watchPersons();
    }

}
