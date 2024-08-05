package com.turismoapp.turismo_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turismoapp.turismo_app.dto.LoginRequest;
import com.turismoapp.turismo_app.model.Usuario;
import com.turismoapp.turismo_app.service.UsuarioService;

@RestController
@CrossOrigin (origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrarUsuario")
    public String addUsuario(@RequestBody Usuario usuario) {
        return usuarioService.usuarioAddUpdate(usuario, "cadastrar");
    }

    @PutMapping("/alterarUsuario")
    public String updateUsuario(@RequestBody Usuario usuario) {
        return usuarioService.usuarioAddUpdate(usuario, "alterar");
    }

    @DeleteMapping("/removerUsuario/{idUsuario}")
    public String deleteUsuario(@PathVariable String idUsuario) {
        return usuarioService.deleteUsuario(idUsuario);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.login(request.getEmail(), request.getSenha());
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed!");
    }

    @GetMapping("/listarUsuarios")
    public Iterable<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }
}
