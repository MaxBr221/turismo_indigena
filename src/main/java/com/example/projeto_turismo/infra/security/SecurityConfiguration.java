package com.example.projeto_turismo.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final SecurityFilter securityFilter;

    public SecurityConfiguration(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                // 🌍 CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 🧨 CSRF desativado (API stateless)
                .csrf(csrf -> csrf.disable())

                // 🧠 SEM sessão (JWT)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 🔐 regras de acesso
                .authorizeHttpRequests(auth -> auth

                        // 🔓 AUTH SEMPRE LIBERADO
                        .requestMatchers("/auth/**").permitAll()

                        // 🔓 SWAGGER LIBERADO
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // 🔓 GETs públicos (ajuste conforme regra real do teu sistema)
                        .requestMatchers(HttpMethod.GET,
                                "/guide/**",
                                "/pontoTuristico/**",
                                "/restaurantes/**",
                                "/avaliacao/**"
                        ).permitAll()

                        // 🔐 ADMIN ONLY
                        .requestMatchers(HttpMethod.POST,
                                "/guide/**",
                                "/pontoTuristico/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE,
                                "/guide/**",
                                "/pontoTuristico/**"
                        ).hasRole("ADMIN")

                        // 🔐 USER + ADMIN
                        .requestMatchers(HttpMethod.POST,
                                "/agendamento/**"
                        ).hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.DELETE,
                                "/agendamento/**"
                        ).hasAnyRole("USER", "ADMIN")

                        // 🔐 resto fechado
                        .anyRequest().authenticated()
                )

                // ⚠️ IMPORTANTE: filtro JWT
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🌍 CORS LIMPO E GLOBAL
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "http://localhost:3001"
        ));

        config.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}