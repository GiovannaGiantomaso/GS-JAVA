package br.fiap.ajuda.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/",
                                "/public/**",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        .requestMatchers(
                                "/usuarios/perfil",
                                "/usuarios/salvar",
                                "/pedidos/**",
                                "/ai/**",
                                "/principal"
                        ).authenticated()

                        .anyRequest().authenticated()
                )

                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/public/login") // sua página de login
                        .defaultSuccessUrl("/principal", true)
                )

                // Configuração de logout
                .logout(logout -> logout
                        .logoutSuccessUrl("/public/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}
