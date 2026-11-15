package br.com.raizesdonordeste.backend.application;

import br.com.raizesdonordeste.backend.domain.Usuario;
import br.com.raizesdonordeste.backend.infrastructure.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ListarUsuariosUseCase {
    private final UsuarioRepository repository;

    public ListarUsuariosUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Iterable<Usuario> executar() {
        return repository.findAll();
    }
}