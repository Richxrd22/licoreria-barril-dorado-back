package com.barrildorado.lbd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.barrildorado.lbd.jwt.JwtAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
  private JwtAccessDeniedHandler jwtAccessDeniedHandler;

  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Autowired
  private AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authRequest -> authRequest
            .requestMatchers("/auth/**").permitAll() // Rutas públicas

            .requestMatchers("/categoria/registrar").hasRole("ADMIN") 
            .requestMatchers("/categoria/listar").hasAnyRole("ADMIN", "USER") 
            .requestMatchers("/categoria/buscar/**").hasAnyRole("ADMIN", "USER") 
            .requestMatchers("/categoria/actualizar/**").hasRole("ADMIN") 
            .requestMatchers("/categoria/eliminar/**").hasRole("ADMIN") 

            .requestMatchers("/empleado/registrar").hasRole("ADMIN") 
            .requestMatchers("/empleado/listar").hasAnyRole("ADMIN", "USER") 
            .requestMatchers("/empleado/buscar/**").hasAnyRole("ADMIN", "USER") 
            .requestMatchers("/empleado/actualizar/**").hasRole("ADMIN") 
            .requestMatchers("/empleado/eliminar/**").hasRole("ADMIN") 

            .requestMatchers("/empresa/registrar").hasRole("ADMIN") 
            .requestMatchers("/empresa/listar").hasAnyRole("ADMIN", "USER") 
            .requestMatchers("/empresa/buscar/**").hasAnyRole("ADMIN", "USER") 
            .requestMatchers("/empresa/actualizar/**").hasRole("ADMIN") 
            .requestMatchers("/empresa/eliminar/**").hasRole("ADMIN") 

            .requestMatchers("/producto/registrar").hasRole("ADMIN") 
            .requestMatchers("/producto/listar").hasAnyRole("ADMIN", "USER") 
            .requestMatchers("/producto/buscar/**").hasAnyRole("ADMIN", "USER") 
            .requestMatchers("/producto/actualizar/**").hasRole("ADMIN") 
            .requestMatchers("/producto/eliminar/**").hasRole("ADMIN") 

            .requestMatchers("/proveedor/registrar").hasRole("ADMIN") 
            .requestMatchers("/proveedor/listar").hasAnyRole("ADMIN", "USER") 
            .requestMatchers("/proveedor/buscar/**").hasAnyRole("ADMIN", "USER") 
            .requestMatchers("/proveedor/actualizar/**").hasRole("ADMIN") 
            .requestMatchers("/proveedor/eliminar/**").hasRole("ADMIN") 

            .requestMatchers("/rol/registrar").hasRole("ADMIN") 
            .requestMatchers("/rol/listar").hasRole("ADMIN") 
            .requestMatchers("/rol/buscar/**").hasRole("ADMIN") 
            .requestMatchers("/rol/actualizar/**").hasRole("ADMIN") 
            .requestMatchers("/rol/eliminar/**").hasRole("ADMIN") 

            .anyRequest().authenticated())
            
        .exceptionHandling(exceptionHandling -> exceptionHandling
            .authenticationEntryPoint(jwtAuthenticationEntryPoint) 
            .accessDeniedHandler(jwtAccessDeniedHandler)
        )
        .sessionManagement(
            sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) 
        .build();
  }
}
