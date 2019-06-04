package com.divae.graphql.graphqlspringboot.security;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@PreAuthorize("hasRole('USER')")
public @interface RequireUser {
}
