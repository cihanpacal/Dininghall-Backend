package com.cihanpacal.dininghall.config.security;

import com.cihanpacal.dininghall.model.entity.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationTokenFilter authenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/api/1.0/authentication/login").permitAll()
                .antMatchers("/api/1.0/users/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers("/api/1.0/user-logs/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers("/api/1.0/bills/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers("/api/1.0/bill-products/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.POST,"/api/1.0/dining-halls/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.DELETE,"/api/1.0/dining-halls/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.PUT,"/api/1.0/dining-halls/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.POST,"/api/1.0/food-groups/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.DELETE,"/api/1.0/food-groups/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.PUT,"/api/1.0/food-groups/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.POST,"/api/1.0/measurement-units/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.DELETE,"/api/1.0/measurement-units/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.PUT,"/api/1.0/measurement-units/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.POST,"/api/1.0/products/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.DELETE,"/api/1.0/products/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.PUT,"/api/1.0/products/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.POST,"/api/1.0/product-groups/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.DELETE,"/api/1.0/product-groups/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.PUT,"/api/1.0/product-groups/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.POST,"/api/1.0/stocks/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.DELETE,"/api/1.0/stocks/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.PUT,"/api/1.0/stocks/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers("/api/1.0/stock-transactions/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.POST,"/api/1.0/warehouses/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.DELETE,"/api/1.0/warehouses/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers(HttpMethod.PUT,"/api/1.0/warehouses/**").hasRole(Role.ROLE_ADMIN.toString().substring(5))
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/1.0/account/forget-password").permitAll()
                .antMatchers("/api/1.0/account/reset-password/**").permitAll()
                .antMatchers("/api/1.0/account/confirm/**").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            String message="Yetkisiz Erişim";
            Map<String,String> errors=new HashMap<>();
            errors.put("message",message);

            SecurityErrorResponse errorResponse=SecurityErrorResponse.builder()
                    .path(request.getRequestURI())
                    .message(message)
                    .status(HttpStatus.FORBIDDEN.value())
                    .errors(errors)
                    .build();

            byte[] body=new ObjectMapper().writeValueAsBytes(errorResponse);

            response.getOutputStream().write(body);
        });

        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

                response.setStatus(HttpStatus.UNAUTHORIZED.value());

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                String message="Yetkisiz Erişim";
                Map<String,String> errors=new HashMap<>();
                errors.put("message",message);

                SecurityErrorResponse errorResponse=SecurityErrorResponse.builder()
                        .path(request.getRequestURI())
                        .message(message)
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .errors(errors)
                        .build();

                byte[] body=new ObjectMapper().writeValueAsBytes(errorResponse);

                response.getOutputStream().write(body);
            }
        });

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
