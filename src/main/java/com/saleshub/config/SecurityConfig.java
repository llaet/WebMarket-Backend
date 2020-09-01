package com.saleshub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**",
            };

    private static final String[] PUBLIC_GET_MATCHERS = {
            "/produtos/**",
            "/categorias/**"};

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            httpSecurity.headers().frameOptions().disable();
        }

        httpSecurity.cors().and().csrf().disable();

        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.GET,PUBLIC_GET_MATCHERS).permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){

        final UrlBasedCorsConfigurationSource urlConfiguration = new UrlBasedCorsConfigurationSource();
        urlConfiguration.registerCorsConfiguration(
                "/**", new CorsConfiguration().applyPermitDefaultValues());
        return urlConfiguration;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
