package com.webdev.productsystem.Shared.Infrastruture.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/login").permitAll()
                .antMatchers(HttpMethod.POST, "/address/create").permitAll()
                .antMatchers(HttpMethod.POST, "/phone/create").permitAll()
                .antMatchers(HttpMethod.POST, "/user/create").permitAll()
                .antMatchers(HttpMethod.POST, "/city/create").permitAll()
                .antMatchers(HttpMethod.POST, "/hotel/create").permitAll()
                .antMatchers(HttpMethod.POST, "/tour/create").permitAll()
                .antMatchers(HttpMethod.POST, "/touristicLocation/create").permitAll()
                .antMatchers(HttpMethod.GET, "/hotel/all").permitAll()
                .antMatchers(HttpMethod.GET, "/city/all").permitAll()
                .antMatchers(HttpMethod.GET, "/tour/all").permitAll()
                .antMatchers(HttpMethod.GET, "/touristicLocation/all").permitAll()
                .antMatchers(HttpMethod.GET, "/tour/all").permitAll()
                .antMatchers(HttpMethod.GET, "/address/all").permitAll()
                .antMatchers(HttpMethod.GET, "/city/*").permitAll()
                //.antMatchers(HttpMethod.GET, "/user/all").permitAll()
                .antMatchers(HttpMethod.GET, AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated();
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
    }

}
