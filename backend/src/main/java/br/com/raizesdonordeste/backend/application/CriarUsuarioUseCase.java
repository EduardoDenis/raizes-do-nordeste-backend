package br.com.raizesdonordeste.backend.application;

import br.com.raizesdonordeste.backend.domain.Usuario;
import br.com.raizesdonordeste.backend.infrastructure.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioUseCase {

    private final UsuarioRepository repository;

    public CriarUsuarioUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario executar(Usuario usuario) {

        return repository.save(usuario);
    }
}