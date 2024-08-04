package com.turismoapp.turismo_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.turismoapp.turismo_app.model.Usuario;
import com.turismoapp.turismo_app.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String usuarioAddUpdate(Usuario usuario, String acao) {

        if(acao.equals("cadastrar")) {
            new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.CREATED);
            return String.valueOf(usuario.getId());

        } else {
            new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.OK);
            return String.valueOf(usuario.getId());
        }
        
    }

    public String deleteUsuario(String idUsuario) {
        usuarioRepository.deleteById(Integer.valueOf(idUsuario));
        return idUsuario;
    }

    public Optional<Usuario> getUsuario(int idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public Iterable<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
   
    public Usuario login(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
}
