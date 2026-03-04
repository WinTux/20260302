package com.pepe.proyectospringboot01.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Pruebas {
	private static final Logger logger = LoggerFactory.getLogger(Pruebas.class);
	@Autowired
	private ApplicationContext contexto;
	
	@RequestMapping("/cerrar") // http://localhost:7001/cerrar
	@ResponseBody
	public void cerrar() {
		logger.debug("Se ingresó al método cerrar() del controlador Pruebas.");
		System.out.println("CERRANDO APLICACIÓN");
		logger.info("El servicio se apagará.");
		SpringApplication.exit(contexto);
	}
}
