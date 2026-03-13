package com.pepe.proyectospringboot01.Filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import tools.jackson.databind.ObjectMapper;
@Component
@Order(1)
public class ControlConexionFilter implements Filter{
	
	@Autowired
	ObjectMapper objectMapper;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("[FILTRO] Remote host: "+request.getRemoteHost());
		System.out.println("[FILTRO] Remote address: "+request.getRemoteAddr());
		chain.doFilter(request, response);
	}

}