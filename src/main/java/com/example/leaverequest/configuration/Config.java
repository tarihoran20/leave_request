package com.example.leaverequest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class Config {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {

        return auth.getAuthenticationManager();

    }

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((auth) -> {
            try {
                auth

                        .antMatchers("/api/division/**").permitAll()
                        .antMatchers("/api/usermanagement/register").permitAll()
                        .antMatchers("/api/usermanagement/login").permitAll()
                        .antMatchers("/api/leave/getAll").permitAll()
                        .antMatchers("/api/employeeAccount/getData").permitAll()
                        .antMatchers("/api/employeeAccount/getEmployeeBackupList").permitAll()
                        .antMatchers("/api/request/insert").permitAll()
                        .antMatchers("/api/approvalbackup/getAllRequested").permitAll()
                        .antMatchers("/api/approvalmanager/getAllRequested").permitAll()
                        .antMatchers("/api/approvalbackup/**").permitAll()
                        .antMatchers("/api/approvalmanager/**").permitAll()
                        .antMatchers("/api/statusallrequest/getAll/**").permitAll()

                        // .antMatchers("/division/**").hasAuthority("Administration")
                        // .antMatchers("/employee/**").hasAuthority("Employee")
                        .antMatchers("/dashboard/**").hasAuthority("Employee")
                        .antMatchers("/dashboard/**").hasAuthority("Manager")
                        .antMatchers("/api/approvalhr/**").hasAuthority("HR")

                        .anyRequest()
                        .authenticated()
                        .and()
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                        // .and()
                        .formLogin()
                        .loginPage("/usermanagement/login").permitAll()
                        .and()
                        .httpBasic()
                        .and()
                        .logout()

                ;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return http.build();
    }

    @Bean
    public PasswordEncoder PasswordHasing() {
        return new BCryptPasswordEncoder();
    }
}
