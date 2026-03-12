package com.pepe.proyectospringboot01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class EstudianteInterceptor implements HandlerInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(EstudianteInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
	                         HttpServletResponse response,
	                         Object handler) throws Exception {

	    if(request.getMethod().equals("PUT")) {
	    	logger.info(String.format("Usuario %s, host: %s desde la direccion: %s:%d Editará al estudiante %s (%s)", 
	    			request.getRemoteUser(), request.getRemoteHost(),
	    			request.getRemoteAddr(), request.getRemotePort(),
	    			request.getRequestURI().split("/")[4],
	    			request.getRequestURI()
	    			));
	    }

	    return true; // true = continuar, false = detener
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
	                       HttpServletResponse response,
	                       Object handler,
	                       ModelAndView modelAndView) throws Exception {

	    System.out.println("Después del controller");
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
	                            HttpServletResponse response,
	                            Object handler,
	                            Exception ex) throws Exception {

	    System.out.println("Petición finalizada");
	}
}
