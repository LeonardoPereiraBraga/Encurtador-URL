package io.github.Leonardo.Encurtador.URL.controller;

import io.github.Leonardo.Encurtador.URL.dto.UsuarioLoginRequest;
import io.github.Leonardo.Encurtador.URL.dto.UsuarioLoginResponse;
import io.github.Leonardo.Encurtador.URL.dto.UsuarioRegisterRequest;
import io.github.Leonardo.Encurtador.URL.dto.UsuarioRegisterResponse;
import io.github.Leonardo.Encurtador.URL.entities.Usuario;
import io.github.Leonardo.Encurtador.URL.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<UsuarioRegisterResponse> registrar(@RequestBody UsuarioRegisterRequest request){
        UsuarioRegisterResponse usuario = usuarioService.registrarUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginResponse> logar(@RequestBody UsuarioLoginRequest request){
        UsuarioLoginResponse loginResponse = usuarioService.logarUsuario(request);
        return ResponseEntity.ok(loginResponse);
    }

}
