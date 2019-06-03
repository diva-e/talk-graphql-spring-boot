package com.divae.graphql.graphqlspringboot.person;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.TopicProcessor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class PersonHolder {

    private Map<UUID, Person> personStorage = new ConcurrentHashMap<>();
    private TopicProcessor<Person> topic = TopicProcessor.<Person>builder()
            .autoCancel(false)
            .bufferSize(1)
            .build();
    private FluxSink<Person> sink = topic.sink(FluxSink.OverflowStrategy.LATEST);

    public List<Person> getPersons() {
        return new ArrayList<>(personStorage.values());
    }

    public Person getPerson(UUID id) {
        return personStorage.get(id);
    }

    public Person updateOrCreatePerson(Person source) {
        Person target;
        if (null == source.getId()) {
            target = Person.builder()
                    .id(UUID.randomUUID())
                    .build();
            personStorage.put(target.getId(), target);
        } else if (personStorage.containsKey(source.getId())) {
            target = personStorage.get(source.getId());
        } else {
            throw new IllegalArgumentException("Person with id " + source.getId() + " does not exist");
        }

        transfer(source::getFirstName, target::setFirstName);
        transfer(source::getLastName, target::setLastName);

        transfer(source::getStreet, target::setStreet);
        transfer(source::getHouseNumber, target::setHouseNumber);
        transfer(source::getZipCode, target::setZipCode);
        transfer(source::getCity, target::setCity);

        transfer(source::getEmail, target::setEmail);
        transfer(source::getTelephone, target::setTelephone);

        target.setLastModified(Instant.now());

        sink.next(target);

        return target;
    }

    public Publisher<Person> watchPersons() {
        return topic;
    }

    private <T> void transfer(Supplier<T> getter, Consumer<T> setter) {
        T value = getter.get();
        if (null != value) {
            setter.accept(value);
        }
    }

}

