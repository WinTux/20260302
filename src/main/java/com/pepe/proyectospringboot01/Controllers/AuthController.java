package com.pepe.proyectospringboot01.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepe.proyectospringboot01.DTO.JwtResponse;
import com.pepe.proyectospringboot01.DTO.LoginRequest;
import com.pepe.proyectospringboot01.Services.JwtUtil;
import com.pepe.proyectospringboot01.Services.UsuarioService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UsuarioService usuarioService;
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest request){
		if(usuarioService.validarCredenciales(request.getUsername(), request.getPassword())) {
			String token = jwtUtil.generateToken(request.getUsername());
			return ResponseEntity.ok(new JwtResponse(token));
		}else
			return ResponseEntity.status(401).body("Credenciales no validas.");
	}
}