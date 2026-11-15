package br.com.raizesdonordeste.backend.application;

import br.com.raizesdonordeste.backend.domain.Usuario;
import br.com.raizesdonordeste.backend.infrastructure.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUsuarioUseCase {

    private final UsuarioRepository repository;

    public AtualizarUsuarioUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario executar(Long id, Usuario dadosAtualizados) {
        return repository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNome(dadosAtualizados.getNome());
            usuarioExistente.setEmail(dadosAtualizados.getEmail());
            usuarioExistente.setSenha(dadosAtualizados.getSenha());
            usuarioExistente.setPerfil(dadosAtualizados.getPerfil());
            return repository.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }
}