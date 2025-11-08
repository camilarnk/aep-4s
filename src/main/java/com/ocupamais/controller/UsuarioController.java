package com.ocupamais.controller;

import com.ocupamais.dto.UsuarioDTO;
import com.ocupamais.model.Usuario;
import com.ocupamais.service.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

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
                .map(u -> new UsuarioDTO(u.getId(), u.getNome(), u.getEmail(), u.getTipo()))
                .collect(Collectors.toList());
    }

    // POST /usuarios - criar novo usuário
    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) { // validações de cadastro
            String mensagemErro = result.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(mensagemErro);
        }
        
        Usuario existente = usuarioService.buscarPorEmail(usuario.getEmail());
        if (existente != null && existente.getEmail() != null) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Email já cadastrado. Tente outro.");
        }

        usuarioService.cadastrar(usuario);
        
        UsuarioDTO usuarioDTO = new UsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getTipo()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    // GET /usuarios/{email} - buscar por email
    @GetMapping("/{email}")
    public UsuarioDTO buscarPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        if (usuario != null) {
            return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTipo());
        }
        return null;
    }

    // PUT /usuarios/{id} - atualizar usuário
    @PutMapping("/{id}")
    public UsuarioDTO atualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        usuarioService.atualizar(usuario);
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTipo());
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

    // POST /usuarios/login - realizar login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Usuario usuarioBanco = usuarioService.buscarPorEmail(usuario.getEmail());

        if (usuarioBanco == null || !usuarioBanco.getSenha().equals(usuario.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha incorretos");
        }

        // Retorna apenas as informações seguras (sem senha)
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuarioBanco.getId(),
                usuarioBanco.getNome(),
                usuarioBanco.getEmail(),
                usuarioBanco.getTipo()
        );
        return ResponseEntity.ok(usuarioDTO);
    }
    
}