package com.pepe.proyectospringboot01.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Primero {
	@RequestMapping("/saludo")
	@ResponseBody
	public String saludoAtodos() {
		return "¡Hola a todos!";
	}
	@RequestMapping("/despedida") //http://localhost:8080/despedida
	@ResponseBody
	public String despedida() {
		return "¡Adiós a todos!";
	}
}
