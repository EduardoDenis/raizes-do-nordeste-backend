package br.com.raizesdonordeste.backend.application;

import br.com.raizesdonordeste.backend.domain.Usuario;
import br.com.raizesdonordeste.backend.infrastructure.UsuarioRepository;
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