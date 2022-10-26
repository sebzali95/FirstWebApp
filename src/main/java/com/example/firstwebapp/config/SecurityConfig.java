package com.example.firstwebapp.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();

        http.authorizeRequests()
                .mvcMatchers("/student/save").access("hasAnyAuthority('ADMIN')")
                .mvcMatchers("/student/delete").access("hasAnyAuthority('ADMIN')")
                .mvcMatchers("/student/update").access("hasAnyAuthority('ADMIN')")
                .mvcMatchers("/student/welcome").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("sebzali")
                        .password("{noop}admin")
                        .roles("ADMIN")
                        .build();
        UserDetails user1 =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("{noop}admin")
                        .roles("User")
                        .build();
        inMemoryUserDetailsManager.createUser(user);
        inMemoryUserDetailsManager.createUser(user1);
        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
