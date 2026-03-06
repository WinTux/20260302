package com.pepe.proyectospringboot01.Controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pepe.proyectospringboot01.Models.Estudiante;

@Controller
public class EstudianteController {
	private static Map<String, Estudiante> estudiantes = new HashMap<>();
	static {
		Estudiante e1 = new Estudiante(1,"Pepe","Perales");
		Estudiante e2 = new Estudiante(2,"Ana","Sosa");
		Estudiante e3 = new Estudiante(3,"Sofía","Rocha");
		estudiantes.put("1", e1);
		estudiantes.put("2", e2);
		estudiantes.put("3", e3);
	}
	@GetMapping("/estudiante")
	public ResponseEntity<Object> getEstudiantes(){
		return new ResponseEntity<>(estudiantes.values(), HttpStatus.OK);
	}
	@GetMapping("/estudiante/{id}")
	public ResponseEntity<Object> getEstudiante(@PathVariable("id") String id){
		return new ResponseEntity<>(estudiantes.get(id),HttpStatus.OK);
	}
	@PostMapping("/estudiante")
	public ResponseEntity<Object> nuevoEstudiante(@RequestBody Estudiante est){
		estudiantes.put(est.getId()+"", est);
		URI ubicacionRecurso = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(est.getId())
				.toUri();
		return ResponseEntity.created(ubicacionRecurso).body(est);
	}
	@PutMapping("/estudiante/{id}")
	public ResponseEntity<Object> modificarEstudiante(@PathVariable("id") String id,@RequestBody Estudiante est){
		estudiantes.remove(id);
		est.setId(Integer.parseInt(id));
		estudiantes.put(id, est);
		return new ResponseEntity<>("Se actualizó el estudiante "+id, HttpStatus.NO_CONTENT);
	}
}
