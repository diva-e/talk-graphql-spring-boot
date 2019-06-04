package com.divae.graphql.graphqlspringboot;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@GraphQLTest
class PersonGraphQlTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void createPersonAndFetchPersons() throws IOException {
        GraphQLResponse createPersonResponse = graphQLTestTemplate.perform("graphql/createPerson.graphql", null);

        assertThat(createPersonResponse.get("$.data.changePerson.id"), not(isEmptyOrNullString()));

        GraphQLResponse fetchPersonsResponse = graphQLTestTemplate.perform("graphql/fetchPersons.graphql", null);

        assertThat(fetchPersonsResponse.get("$.data.persons[0].firstName"), is("Hans"));
    }

    @Test
    void createPersonAndFetchPerson() throws IOException {
        GraphQLResponse createPersonResponse = graphQLTestTemplate.perform("graphql/createPerson.graphql", null);

        String personId = createPersonResponse.get("$.data.changePerson.id");

        ObjectNode variables = VariablesUtil.createVariables();
        variables.put("personId", personId);
        GraphQLResponse fetchPersonResponse = graphQLTestTemplate.perform("graphql/fetchPerson.graphql", variables);

        assertThat(fetchPersonResponse.get("$.data.person.firstName"), is("Hans"));
    }

}