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
class ContractGraphQlTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void createAndFetchPerson() throws IOException {
        GraphQLResponse createPersonResponse = graphQLTestTemplate.perform("graphql/createPerson.graphql", null);

        String personId = createPersonResponse.get("$.data.changePerson.id");

        ObjectNode variables = VariablesUtil.createVariables();
        variables.put("personId", personId);
        GraphQLResponse createContractResponse = graphQLTestTemplate.perform("graphql/createContract.graphql", variables);

        assertThat(createContractResponse.get("$.data.addContractToPerson.id"), not(isEmptyOrNullString()));

        GraphQLResponse fetchPersonResponse = graphQLTestTemplate.perform("graphql/fetchPerson.graphql", variables);

        assertThat(fetchPersonResponse.get("$.data.person.contracts[0].name"), is("Haftpflichtversicherung"));
    }

}