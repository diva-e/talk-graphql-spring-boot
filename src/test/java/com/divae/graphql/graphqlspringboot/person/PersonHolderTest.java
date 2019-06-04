package com.divae.graphql.graphqlspringboot.person;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PersonHolderTest {

    Person testPerson = Person.builder()
            .gender(Person.Gender.MALE)
            .firstName("Hans")
            .lastName("Dampf")
            .street("Rathausplatz")
            .houseNumber(123)
            .zipCode("76131")
            .city("Karlsruhe")
            .email("hans.dampf@example.com")
            .telephone("+49123456789")
            .sendNewsletter(true)
            .build();

    PersonHolder personHolder = new PersonHolder();

    @Test
    void updateOrCreatePerson() {
        Person createdPerson = personHolder.updateOrCreatePerson(testPerson);

        assertThat(createdPerson, is(notNullValue()));
        assertThat(createdPerson.getId(), is(notNullValue()));
        assertThat(createdPerson.getLastModified(), is(notNullValue()));
        assertThat(createdPerson.getGender(), is(Person.Gender.MALE));
        assertThat(createdPerson.getFirstName(), is("Hans"));
        assertThat(createdPerson.getLastName(), is("Dampf"));
        assertThat(createdPerson.getStreet(), is("Rathausplatz"));
        assertThat(createdPerson.getHouseNumber(), is(123));
        assertThat(createdPerson.getZipCode(), is("76131"));
        assertThat(createdPerson.getCity(), is("Karlsruhe"));
        assertThat(createdPerson.getEmail(), is("hans.dampf@example.com"));
        assertThat(createdPerson.getTelephone(), is("+49123456789"));
        assertThat(createdPerson.isSendNewsletter(), is(true));
    }

    @Test
    void getPersons() {
        Person createdPerson = personHolder.updateOrCreatePerson(testPerson);

        List<Person> loadedPersons = personHolder.getPersons();

        assertThat(loadedPersons, contains(createdPerson));
    }

    @Test
    void getPerson() {
        Person createdPerson = personHolder.updateOrCreatePerson(testPerson);

        Person loadedPerson = personHolder.getPerson(createdPerson.getId());

        assertThat(loadedPerson, is(createdPerson));
    }


    @Test
    void watchPersons() {
        Person createdPerson = personHolder.updateOrCreatePerson(testPerson);

        Person watchedPerson = personHolder.watchPersons().blockFirst();

        assertThat(watchedPerson, is(createdPerson));
    }

}