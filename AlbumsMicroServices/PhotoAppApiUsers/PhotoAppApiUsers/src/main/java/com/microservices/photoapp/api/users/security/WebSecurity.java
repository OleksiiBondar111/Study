package com.microservices.photoapp.api.users.security;


import com.microservices.photoapp.api.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurity {

    private final Environment environment;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsersService usersService;


    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // Configure AuthenticationManagerBuilder
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(usersService)
                .passwordEncoder(bCryptPasswordEncoder);

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        // Create AuthenticationFilter
        AuthenticationFilter authenticationFilter =
                new AuthenticationFilter(usersService, environment, authenticationManager);
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));

        http.csrf((csrf) -> csrf.disable());

        http.authorizeHttpRequests((authz) -> authz
//                        .requestMatchers(new AntPathRequestMatcher("/users/**")).access(
//                                new WebExpressionAuthorizationManager("hasIpAddress('"+environment.getProperty("gateway.ip")+"')"))
                                .requestMatchers(new AntPathRequestMatcher("/users/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/auth/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/v3/api/docs.yaml")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/actuator/**")).permitAll()
                )
                .addFilter(new AuthorizationFilter(authenticationManager, environment))
                .addFilter(authenticationFilter)
                .authenticationManager(authenticationManager)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin()));
        DefaultSecurityFilterChain build = http.build();
        return build;
    }

}
