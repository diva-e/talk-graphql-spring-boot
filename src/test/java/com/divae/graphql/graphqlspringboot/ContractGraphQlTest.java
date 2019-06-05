package com.divae.graphql.graphqlspringboot;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@GraphQLTest
class ContractGraphQlTest {
    private static Logger log = LoggerFactory.getLogger(ContractGraphQlTest.class);

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void createAndFetchPerson() throws IOException {
        GraphQLResponse createPersonResponse = graphQLTestTemplate.perform("graphql/createPerson.graphql", null);

        String personId = createPersonResponse.get("$.data.changePerson.id");
        assertThat(personId, not(isEmptyOrNullString()));

        ObjectNode variables = VariablesUtil.createVariables();
        variables.put("name", "KFZ Versicherung");
        variables.put("numberPlate", "KA-AB 765");
        GraphQLResponse createContractResponse = graphQLTestTemplate.perform("graphql/createContractCarInsurance.graphql", variables);

        String contractId = createContractResponse.get("$.data.createContractCarInsurance.id");
        assertThat(contractId, not(isEmptyOrNullString()));

        variables.removeAll();
        variables.put("personId", personId);
        variables.put("contractId", contractId);
        GraphQLResponse assignContractToPersonResponse = graphQLTestTemplate.perform("graphql/assignContractToPerson.graphql", variables);

        variables.removeAll();
        variables.put("personId", personId);
        GraphQLResponse fetchPersonResponse = graphQLTestTemplate.perform("graphql/fetchPerson.graphql", variables);

        assertThat(fetchPersonResponse.get("$.data.person.contracts[0].name"), is("KFZ Versicherung"));
        assertThat(fetchPersonResponse.get("$.data.person.contracts[0].numberPlate"), is("KA-AB 765"));
    }

}