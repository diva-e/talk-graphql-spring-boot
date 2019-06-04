package com.divae.graphql.graphqlspringboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VariablesUtil {

    ObjectNode createVariables() {
        return new ObjectMapper().createObjectNode();
    }

}
