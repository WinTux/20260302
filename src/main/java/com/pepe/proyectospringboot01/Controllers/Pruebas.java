package com.pepe.proyectospringboot01.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pepe.proyectospringboot01.Models.Persona;

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
	@PostMapping("/persona") // http://localhost:7001/persona [POST]
	@ResponseBody
	public String pruebaMetodo(@RequestBody Persona p) {
		System.out.printf("Nombre completo: %s %s de edad %d", p.nombre,p.apellido,p.edad);
		return "HECHO";
	}
	@GetMapping("/algo/{cosita}") // http://localhost:7001/algo/{parametro}
	public void pruebaParam(@PathVariable("cosita") String x) {
		System.out.println(x);
	}
}
