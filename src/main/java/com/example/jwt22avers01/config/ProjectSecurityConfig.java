package com.example.jwt22avers01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccount", "/myBalance").authenticated()
                        .requestMatchers("/contact", "/register").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
        // var obj = (SecurityFilterChain)http.build();  debug her for at se indbyggede filtre i Spring Security
        //return (SecurityFilterChain)http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        //ingen kryptering return NoOpPasswordEncoder.getInstance();
        //med 20 tager det lang tid at kryptere return new BCryptPasswordEncoder(20);
        return new BCryptPasswordEncoder();
    }

}
