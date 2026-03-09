package com.pepe.proyectospringboot01.Controllers;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pepe.proyectospringboot01.Models.Estudiante;


@Controller
@RequestMapping("/api/v1")
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
	@DeleteMapping("/estudiante/{id}")
	public ResponseEntity<Object> elminarEstudiante(@PathVariable("id") String id){
		estudiantes.remove(id);
		return new ResponseEntity<>("Se eleminó el estudiante "+id, HttpStatus.NO_CONTENT);
	}
	@PatchMapping("/estudiante/{id}")
	public ResponseEntity<String> editarConPatch(@PathVariable("id") String id, @RequestBody Map<String,Object> atributosModificados){
		Estudiante estOriginal = estudiantes.get(id);
		atributosModificados.forEach((atributo,valorNuevo)->{
			Field campo = ReflectionUtils.findField(Estudiante.class, atributo);
			if(campo!=null) {
				campo.setAccessible(true);
				ReflectionUtils.setField(campo, estOriginal, valorNuevo);
			}
		});
		estudiantes.remove(id);
		estudiantes.put(id, estOriginal);
		return new ResponseEntity<>("Se modificó al estudiante "+ id, HttpStatus.OK);
	}
	
}
