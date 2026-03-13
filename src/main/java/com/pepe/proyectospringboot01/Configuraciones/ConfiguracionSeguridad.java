package com.pepe.proyectospringboot01.Configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.actuate.web.servlet.EndpointRequest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pepe.proyectospringboot01.Filters.ControlConexionFilter;
import com.pepe.proyectospringboot01.Filters.JwtRequestFilter;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {
	@Autowired
	private ControlConexionFilter filtro;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	FilterRegistrationBean<Filter> miFiltroRegBean(){
		FilterRegistrationBean<Filter> fil 
		= new FilterRegistrationBean<Filter>();
		fil.setFilter(filtro);
		fil.addUrlPatterns("/estudiante/*");
		fil.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return fil;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http.authorizeHttpRequests(auth -> auth
						.requestMatchers("/auth/**").permitAll()
						.requestMatchers("/api/v1/estudiantes/**").authenticated()
						.anyRequest().permitAll()
				)
				.csrf(c -> c.disable())
				.cors(cors -> {})
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}