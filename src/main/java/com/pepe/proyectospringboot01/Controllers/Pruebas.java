package com.pepe.proyectospringboot01.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Pruebas {
	@Autowired
	private ApplicationContext contexto;
	
	@RequestMapping("/cerrar") // http://localhost:7001/cerrar
	@ResponseBody
	public void cerrar() {
		System.out.println("CERRANDO APLICACIÓN");
		SpringApplication.exit(contexto);
	}
}
