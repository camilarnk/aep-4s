package com.ocupamais.controller;

import com.ocupamais.dto.UsuarioDTO;
import com.ocupamais.model.Usuario;
import com.ocupamais.service.UsuarioService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET /usuarios - listar todos os usuários
    @GetMapping
    public List<UsuarioDTO> listarTodos() {
        return usuarioService.listarTodos().stream()
                .map(u -> new UsuarioDTO(u.getId(), u.getNome()))
                .collect(Collectors.toList());
    }

    // POST /usuarios - criar novo usuário
    @PostMapping
    public UsuarioDTO criar(@RequestBody Usuario usuario) {
        usuarioService.cadastrar(usuario);
        return new UsuarioDTO(usuario.getId(), usuario.getNome());
    }

    // GET /usuarios/{email} - buscar por email
    @GetMapping("/{email}")
    public UsuarioDTO buscarPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        if (usuario != null) {
            return new UsuarioDTO(usuario.getId(), usuario.getNome());
        }
        return null;
    }

    // PUT /usuarios/{id} - atualizar usuário
    @PutMapping("/{id}")
    public UsuarioDTO atualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        usuarioService.atualizar(usuario);
        return new UsuarioDTO(usuario.getId(), usuario.getNome());
    }

    // DELETE /usuarios/{id} - deletar usuário
    @DeleteMapping("/{id}")
    public String deletarUsuario(@PathVariable int id) {
        boolean removido = usuarioService.deletar(id);
        if(removido) {
            return "Usuário deletado com sucesso!";
        } else {
            return "Usuário não encontrado.";
        }
    }

}