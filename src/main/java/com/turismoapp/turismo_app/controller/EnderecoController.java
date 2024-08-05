package com.turismoapp.turismo_app.controller;

import com.turismoapp.turismo_app.model.Endereco;
import com.turismoapp.turismo_app.service.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "*")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/cadastrarEndereco")
    public String addEndereco(@RequestBody Endereco endereco) {
        return enderecoService.enderecoAddUpdate(endereco, "cadastrar");
    }

    @PutMapping("/alterarEndereco")
    public String updateEndereco(@RequestBody Endereco endereco) {
        return enderecoService.enderecoAddUpdate(endereco, "alterar");
    }

    @DeleteMapping("/removerEndereco/{idEndereco}")
    public String deleteEndereco(@PathVariable String idEndereco) {
        return enderecoService.deleteEndereco(idEndereco);
    }

    @GetMapping("/listarEnderecos")
    public Iterable<Endereco> getAllEnderecos() {
        return enderecoService.getAllEnderecos();
    }
}
