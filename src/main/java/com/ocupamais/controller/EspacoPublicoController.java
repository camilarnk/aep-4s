package com.ocupamais.controller;

import com.ocupamais.model.EspacoPublico;
import com.ocupamais.service.EspacoPublicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/espacos")
public class EspacoPublicoController {

    private final EspacoPublicoService espacoService;

    public EspacoPublicoController(EspacoPublicoService espacoService) {
        this.espacoService = espacoService;
    }

    @GetMapping
    public List<EspacoPublico> listarTodos() {
        return espacoService.listarTodos();
    }

    @PostMapping
    public EspacoPublico criar(@RequestBody EspacoPublico espaco) {
        espacoService.cadastrar(espaco);
        return espaco;
    }

    @PutMapping("/{id}")
    public EspacoPublico atualizar(@PathVariable int id, @RequestBody EspacoPublico espaco) {
        espaco.setId(id);
        espacoService.atualizar(espaco);
        return espaco;
    }

    @DeleteMapping("/{id}")
    public String deletarEspacoPublico(@PathVariable int id) {
        boolean removido = espacoService.deletar(id);
        if(removido) {
            return "Espaço Público deletado com sucesso!";
        } else {
            return "Espaço Público não encontrado.";
        }
    }

}