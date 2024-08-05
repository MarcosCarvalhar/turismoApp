package com.turismoapp.turismo_app.controller;

import com.turismoapp.turismo_app.model.Categoria;
import com.turismoapp.turismo_app.service.CategoriaService;

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
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/cadastrarCategoria")
    public String addCategoria(@RequestBody Categoria categoria) {
        return categoriaService.categoriaAddUpdate(categoria, "cadastrar");
    }

    @PutMapping("/alterarCategoria")
    public String updateCategoria(@RequestBody Categoria categoria) {
        return categoriaService.categoriaAddUpdate(categoria, "alterar");
    }

    @DeleteMapping("/removerCategoria/{idCategoria}")
    public String deleteCategoria(@PathVariable String idCategoria) {
        return categoriaService.deleteCategoria(idCategoria);
    }

    @GetMapping("/listarCategorias")
    public Iterable<Categoria> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }
}
