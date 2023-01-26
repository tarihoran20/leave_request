package com.example.leaverequest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class Config{

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {

        return auth.getAuthenticationManager();
    
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((auth) -> {
            try {
                auth
                    .antMatchers("/usermanagement/**").permitAll()
                    .antMatchers("/leave/**").permitAll()
                    .antMatchers("/request/**").permitAll()

                    .antMatchers("/division/**").hasAuthority("Administration")
                    .antMatchers("/employee/**").hasAuthority("Employee")
                    .antMatchers("/api/**").hasAuthority("Employee")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/usermanagement/login").permitAll()
                    .and()
                    .httpBasic()
                    .and()
                    .logout();
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        });
        return http.build();
    }

    @Bean
    public PasswordEncoder PasswordHasing(){
        return new BCryptPasswordEncoder();
    }
}
