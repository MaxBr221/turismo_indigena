package com.example.projeto_turismo.infra.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private SecurityFilter securityFilter;

    public SecurityConfiguration(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf->csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize-> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/google").permitAll()
                        .requestMatchers(HttpMethod.POST, "/agendamento").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/agendamento").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/guide").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/guide").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/guide").permitAll()
                        .requestMatchers(HttpMethod.GET, "/guide/busca").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pontoTuristico").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pontoTuristico").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pontoTuristico").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pontoTuristico/busca").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pontoTuristico/pontos").permitAll()
                       // .requestMatchers(HttpMethod.POST, "/restaurantes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/restaurantes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurantes/restaurantePaginacao").permitAll()
                        .requestMatchers(HttpMethod.GET, "/restaurantes/busca").permitAll()
                        .requestMatchers(HttpMethod.POST, "/restaurantes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pontoTuristico/{id}/imagem").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/mePerfil").permitAll()
                        .requestMatchers(HttpMethod.GET, "/restaurantes/dina").permitAll()
                        .requestMatchers(HttpMethod.POST, "/avaliacao/avaliarRestaurante").permitAll()
                        .requestMatchers(HttpMethod.POST, "/avaliacao/avaliarPontoTuristico").permitAll()
                        .requestMatchers(HttpMethod.GET, "/avaliacao/id").permitAll()
                        .requestMatchers("/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Libera a porta do React/Next.js
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:3001"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
