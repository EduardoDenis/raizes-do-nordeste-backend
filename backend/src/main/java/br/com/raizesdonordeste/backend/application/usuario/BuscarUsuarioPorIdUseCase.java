package br.com.raizesdonordeste.backend.application.usuario;

import br.com.raizesdonordeste.backend.domain.usuario.Usuario;
import br.com.raizesdonordeste.backend.infrastructure.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioPorIdUseCase {
    private final UsuarioRepository repository;

    public BuscarUsuarioPorIdUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario executar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }
}