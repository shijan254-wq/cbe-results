package com.cbe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/", "/*.html", "/css/**", "/js/**").permitAll()
                .requestMatchers("/api/matokeo/yangu").hasRole("MWANAFUNZI")
                .requestMatchers("/api/matokeo/pdf").hasRole("MWANAFUNZI")
                .requestMatchers("/api/matokeo/yote").hasAnyRole("MWALIMU", "ADMIN")
                .requestMatchers("/api/matokeo/tafuta").hasAnyRole("MWALIMU", "ADMIN")
                .requestMatchers("/api/malipo/yangu").hasRole("MWANAFUNZI")
                .requestMatchers("/api/malipo/yote").hasAnyRole("MWALIMU", "ADMIN", "AFISA_MALIPO")
                .requestMatchers("/api/profile/yangu").authenticated()
                .requestMatchers("/api/user/badilisha-password").authenticated()
                .requestMatchers("/api/user/**").hasRole("ADMIN")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/mwalimu/**").hasAnyRole("MWALIMU", "ADMIN")
                .requestMatchers("/api/ratiba/**").authenticated()
                .requestMatchers("/api/mahudhurio/yangu").hasRole("MWANAFUNZI")
                .requestMatchers("/api/mahudhurio/yote").hasAnyRole("MWALIMU", "ADMIN")
                .requestMatchers("/api/taarifa/zote").authenticated()
                .requestMatchers("/api/taarifa/ongeza").hasAnyRole("MWALIMU", "ADMIN")
                .requestMatchers("/api/ripoti/**").hasAnyRole("MWALIMU", "ADMIN", "AFISA_MALIPO")
                .requestMatchers("/api/export/matokeo-yangu").hasRole("MWANAFUNZI")
                .requestMatchers("/api/export/**").hasAnyRole("MWALIMU", "ADMIN")
                .requestMatchers("/api/statistics/**").hasAnyRole("MWALIMU", "ADMIN")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}