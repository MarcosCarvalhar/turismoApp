package com.turismoapp.turismo_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turismoapp.turismo_app.model.Local;
import com.turismoapp.turismo_app.service.LocalService;

@RestController
public class LocalController {

    @Autowired
    private LocalService localService;

    @PostMapping("/cadastrarLocal")
    public String addLocal(@RequestBody Local local) {
        return localService.localAddUpdate(local, "cadastrar");
    }

    @PutMapping("/alterarLocal")
    public String updateLocal(@RequestBody Local local) {
        return localService.localAddUpdate(local, "alterar");
    }

    @DeleteMapping("/removerLocal/{idLocal}")
    public String deleteLocal(@PathVariable String idLocal) {
        return localService.deleteLocal(idLocal);
    }

    @GetMapping("/listarLocais")
    public Iterable<Local> getAllLocais() {
        return localService.getAllLocais();
    }

    @GetMapping("/listarLocaisPorCategoria/{categoriaId}")
    public ResponseEntity<List<Local>> getLocaisPorCategoriaId(@PathVariable String categoriaId) {
        System.out.println("passou aqui");
        List<Local> locais = localService.getLocaisPorCategoriaId(Long.valueOf(categoriaId));
        return ResponseEntity.ok(locais);
    }

    @GetMapping("/listarLocaisPorEstado/{ufEstado}")
    public ResponseEntity<List<Local>> getLocaisPorEstado(@PathVariable String ufEstado) {
        List<Local> locais = localService.getLocaisPorEstado(ufEstado);
        return ResponseEntity.ok(locais);
    }
}
