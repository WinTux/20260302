package com.pepe.proyectospringboot01.Controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Primero {
	private static final Logger logger = Logger.getLogger(Primero.class.getName());
	@Value("${valor.secreto}")
	private String val;
	@RequestMapping("/saludo")
	@ResponseBody
	public String saludoAtodos() {
		logger.info("Se procede a ejecutar el endpoint /saludo");
		return "¡Hola a todos!";
	}
	@RequestMapping("/despedida") //http://localhost:7001/despedida
	@ResponseBody
	public String despedida() {
		return "¡Adiós a todos!";
	}
	@RequestMapping("/")
	@ResponseBody
	public String rescatandoValores() {
		return "El valor secreto es: "+val;
	}
}
