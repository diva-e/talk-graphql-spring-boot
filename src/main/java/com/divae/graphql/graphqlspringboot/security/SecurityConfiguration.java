package com.divae.graphql.graphqlspringboot.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration { // extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//
//                .authorizeRequests()
//                .antMatchers("/graphiql", "/vendor/**").permitAll()
//                .antMatchers("/graphql", "/subscriptions").authenticated()
//                .anyRequest().denyAll()
//
//                .and()
//                .httpBasic();
//    }

}
