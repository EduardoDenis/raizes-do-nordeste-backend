package br.com.raizesdonordeste.backend.application.usuario;

import br.com.raizesdonordeste.backend.infrastructure.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarUsuarioUseCase {
    private final UsuarioRepository repository;

    public DeletarUsuarioUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        repository.deleteById(id);
    }
}