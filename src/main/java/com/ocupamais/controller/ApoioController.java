package com.ocupamais.controller;

import com.ocupamais.dto.ApoioDTO;
import com.ocupamais.dto.ApoioCreateDTO;
import com.ocupamais.model.Apoio;
import com.ocupamais.model.Publicacao;
import com.ocupamais.model.Usuario;
import com.ocupamais.service.ApoioService;
import com.ocupamais.service.PublicacaoService;
import com.ocupamais.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apoios")
public class ApoioController {

    private final ApoioService apoioService;
    private final UsuarioService usuarioService;
    private final PublicacaoService publicacaoService;

    public ApoioController(ApoioService apoioService, UsuarioService usuarioService, PublicacaoService publicacaoService) {
        this.apoioService = apoioService;
        this.usuarioService = usuarioService;
        this.publicacaoService = publicacaoService;
    }

    @GetMapping
    public List<ApoioDTO> listarTodos() {
        return apoioService.listarTodos().stream()
                .map(a -> new ApoioDTO(
                        a.getId(),
                        a.getUsuario() != null ? a.getUsuario().getNome() : "N/A",
                        a.getPublicacao() != null ? a.getPublicacao().getDescricao() : "N/A"
                ))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ApoioDTO criar(@RequestBody ApoioCreateDTO dto) {
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());
        Publicacao publicacao = publicacaoService.buscarPorId(dto.getPublicacaoId());

        Apoio apoio = new Apoio(usuario, publicacao);
        apoioService.cadastrar(apoio);

        return new ApoioDTO(
                apoio.getId(),
                usuario != null ? usuario.getNome() : "N/A",
                publicacao != null ? publicacao.getDescricao() : "N/A"
        );
    }

    @DeleteMapping("/{id}")
    public String deletarApoio(@PathVariable int id) {
        boolean removido = apoioService.deletar(id);
        return removido ? "Apoio deletado com sucesso!" : "Apoio não encontrado.";
    }
}