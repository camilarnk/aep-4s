package com.ocupamais.controller;

import com.ocupamais.dto.PublicacaoDTO;
import com.ocupamais.dto.PublicacaoCreateDTO;
import com.ocupamais.model.EspacoPublico;
import com.ocupamais.model.Publicacao;
import com.ocupamais.model.Usuario;
import com.ocupamais.service.PublicacaoService;
import com.ocupamais.service.UsuarioService;
import com.ocupamais.service.EspacoPublicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    private final PublicacaoService publicacaoService;
    private final UsuarioService usuarioService;
    private final EspacoPublicoService espacoPublicoService;

    public PublicacaoController(PublicacaoService publicacaoService,
                                UsuarioService usuarioService,
                                EspacoPublicoService espacoPublicoService) {
        this.publicacaoService = publicacaoService;
        this.usuarioService = usuarioService;
        this.espacoPublicoService = espacoPublicoService;
    }

    // GET: listar todas as publicações (apenas informações essenciais via DTO)
    @GetMapping
    public List<PublicacaoDTO> listarTodos() {
        return publicacaoService.listarTodos().stream()
                .map(p -> new PublicacaoDTO(
                        p.getId(),
                        p.getUsuario() != null ? p.getUsuario().getNome() : "N/A",
                        p.getEspaco() != null ? p.getEspaco().getNome() : "N/A",
                        p.getDescricao()
                ))
                .collect(Collectors.toList());
    }

    // POST: criar nova publicação
    @PostMapping
    public PublicacaoDTO criar(@RequestBody PublicacaoCreateDTO dto) {
        // Busca objetos completos pelos IDs
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());
        EspacoPublico espaco = espacoPublicoService.buscarPorId(dto.getEspacoPublicoId());

        // Monta a publicação
        Publicacao publicacao = new Publicacao(usuario, espaco, dto.getDescricao());

        // Salva no banco
        publicacaoService.cadastrar(publicacao);

        // Retorna DTO
        return new PublicacaoDTO(
                publicacao.getId(),
                usuario.getNome(),
                espaco.getNome(),
                publicacao.getDescricao()
        );
    }

    // PUT: atualizar uma publicação existente
    @PutMapping("/{id}")
    public PublicacaoDTO atualizar(@PathVariable int id, @RequestBody PublicacaoCreateDTO dto) {
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());
        EspacoPublico espaco = espacoPublicoService.buscarPorId(dto.getEspacoPublicoId());

        Publicacao publicacao = new Publicacao(usuario, espaco, dto.getDescricao());
        publicacao.setId(id);

        publicacaoService.atualizar(publicacao);

        return new PublicacaoDTO(
                publicacao.getId(),
                usuario.getNome(),
                espaco.getNome(),
                publicacao.getDescricao()
        );
    }

    // DELETE: remover publicação pelo ID
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable int id) {
        boolean removido = publicacaoService.deletar(id);
        return removido ? "Publicação deletada com sucesso!" : "Publicação não encontrada.";
    }
}