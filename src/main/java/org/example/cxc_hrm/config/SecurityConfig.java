package org.example.cxc_hrm.config;

import org.example.cxc_hrm.jwt.JwtTokenFilter;
import org.example.cxc_hrm.jwt.JwtTokenService;
import org.example.cxc_hrm.service.auth.AuthenticationService;
import org.example.cxc_hrm.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private UserServiceImpl userService;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    public SecurityConfig(@Lazy UserServiceImpl userService) {
        this.userService = userService;
    }

    private final String[] permitAll = {"/swagger-ui/**", "/v3/api-docs/**", "/cxc/v1/user/auth/**"};
    private final String[] onlyAdmin = {"/cxc/v1/create","/cxc/v1/update","/cxc/v1/delete{id}","/cxc/v1/get-all"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.addAllowedOrigin("*");
                    corsConfiguration.addAllowedMethod("*");
                    corsConfiguration.addAllowedHeader("*");
                    return corsConfiguration;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requestsConfigurer) ->
                        requestsConfigurer
                                .requestMatchers(permitAll).permitAll()
                                .requestMatchers(onlyAdmin).hasRole("ADMIN")
                                .requestMatchers("/cxc/v1/user/block","cxc/v1/user/unblock").hasAnyRole("ADMIN","SUPER_ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtTokenFilter(jwtTokenService, authenticationService),
                        UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder
                = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
