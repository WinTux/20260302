package com.pepe.proyectospringboot01.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pepe.proyectospringboot01.Exceptions.EstudianteNoEncontradoException;

@ControllerAdvice
public class EstudianteExceptionController {
	@ExceptionHandler(value=EstudianteNoEncontradoException.class)
	public ResponseEntity<Object> unaExcepcion(){
		return new ResponseEntity<>("No se encontró al estudiante", HttpStatus.NOT_FOUND);
	}
}
