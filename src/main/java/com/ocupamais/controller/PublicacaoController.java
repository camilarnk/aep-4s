package com.ocupamais.controller;

import com.ocupamais.dto.PublicacaoDTO;
import com.ocupamais.dto.PublicacaoCreateDTO;
import com.ocupamais.model.EspacoPublico;
import com.ocupamais.model.Publicacao;
import com.ocupamais.model.Usuario;
import com.ocupamais.service.PublicacaoService;
import com.ocupamais.service.UsuarioService;
import com.ocupamais.service.EspacoPublicoService;
import com.ocupamais.service.ApoioService; // ✅ importar o ApoioService
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    private final PublicacaoService publicacaoService;
    private final UsuarioService usuarioService;
    private final EspacoPublicoService espacoPublicoService;
    private final ApoioService apoioService;

    public PublicacaoController(PublicacaoService publicacaoService,
                                UsuarioService usuarioService,
                                EspacoPublicoService espacoPublicoService,
                                ApoioService apoioService) {
        this.publicacaoService = publicacaoService;
        this.usuarioService = usuarioService;
        this.espacoPublicoService = espacoPublicoService;
        this.apoioService = apoioService;
    }

    // GET: listar todas as publicações com contagem de apoios
    @GetMapping
    public List<PublicacaoDTO> listarTodos() {
        return publicacaoService.listarTodos().stream()
            .map(p -> {
                int totalApoios = apoioService.contarPorPublicacao(p.getId());
                return new PublicacaoDTO(
                    p.getId(),
                    totalApoios,
                    p.getUsuario().getNome(),
                    p.getEspaco().getNome(),
                    p.getDescricao(),
                    p.getStatus(),
                    p.getDataCriacao().toLocalDateTime()
                );
            })
            .collect(Collectors.toList());
    }

    // POST: criar nova publicação
    @PostMapping
    public PublicacaoDTO criar(@RequestBody PublicacaoCreateDTO dto) {
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado com o ID " + dto.getUsuarioId());
        }

        String nomeEspaco = dto.getNomeEspaco();
        if (nomeEspaco == null || nomeEspaco.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do espaço é obrigatório.");
        }

        EspacoPublico espaco = espacoPublicoService.buscarPorNome(nomeEspaco.trim());
        if (espaco == null) {
            espaco = new EspacoPublico();
            espaco.setNome(nomeEspaco.trim());
            espacoPublicoService.cadastrar(espaco);
        }

        String status = (dto.getStatus() != null && !dto.getStatus().isBlank())
            ? dto.getStatus().toUpperCase()
            : "PENDENTE";

        Publicacao publicacao = new Publicacao(usuario, espaco, dto.getDescricao());
        publicacao.setStatus(status);
        publicacaoService.cadastrar(publicacao);

        return new PublicacaoDTO(
            publicacao.getId(),
            0, // totalApoios começa em 0
            usuario.getNome(),
            espaco.getNome(),
            publicacao.getDescricao(),
            publicacao.getStatus(),
            publicacao.getDataCriacao().toLocalDateTime()
        );
    }

    // PUT: atualizar publicação existente
    @PutMapping("/{id}")
    public PublicacaoDTO atualizar(@PathVariable int id, @RequestBody PublicacaoCreateDTO dto) {
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());
        EspacoPublico espaco = espacoPublicoService.buscarPorNome(dto.getNomeEspaco());

        Publicacao publicacao = new Publicacao(usuario, espaco, dto.getDescricao());
        publicacao.setId(id);
        publicacao.setStatus(dto.getStatus());

        publicacaoService.atualizar(publicacao);

        return new PublicacaoDTO(
            publicacao.getId(),
            0, // totalApoios não é relevante no PUT
            usuario.getNome(),
            espaco.getNome(),
            publicacao.getDescricao(),
            publicacao.getStatus(),
            LocalDateTime.now()
        );
    }

    // DELETE: remover publicação
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable int id) {
        boolean removido = publicacaoService.deletar(id);
        return removido ? "Publicação deletada com sucesso!" : "Publicação não encontrada.";
    }
}
