package com.divae.graphql.graphqlspringboot.contract;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractSubscriptionResolver implements GraphQLSubscriptionResolver {

    private final ContractHolder contractHolder;

    public Publisher<Contract> watchContracts() {
        return contractHolder.watchContracts();
    }

}
