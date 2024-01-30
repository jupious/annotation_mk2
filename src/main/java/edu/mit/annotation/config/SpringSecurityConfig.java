package edu.mit.annotation.config;

import edu.mit.annotation.service.MemberService;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final AuthenticationFailureHandler customFailHandler;
    private final AuthenticationSuccessHandler customSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests(auth -> {
                    auth
                            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                          //  .requestMatchers("/**").permitAll()
                            .requestMatchers("/error**").permitAll()
                            .requestMatchers("/assets/**").permitAll()
                            .requestMatchers("/fonts/**").permitAll()
                            .requestMatchers("/auth/error").permitAll()
//                            .requestMatchers("/invapi/**").permitAll()
//                            .requestMatchers("/orderapi/**").permitAll()
                            .anyRequest().authenticated();

                }).formLogin(
                        login -> login
                        .loginPage("/main/login")
                        .loginProcessingUrl("/main/login")
                        .defaultSuccessUrl("/main/portal",true).permitAll()
                        .failureUrl("/error").permitAll()
                        .failureHandler(customFailHandler)
                        .successHandler(customSuccessHandler)

                ).logout(Customizer.withDefaults());
        return httpSecurity.build();

    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
