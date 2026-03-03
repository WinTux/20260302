package com.pepe.proyectospringboot01.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Primero {
	@Value("${valor.secreto}")
	private String val;
	@RequestMapping("/saludo")
	@ResponseBody
	public String saludoAtodos() {
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
