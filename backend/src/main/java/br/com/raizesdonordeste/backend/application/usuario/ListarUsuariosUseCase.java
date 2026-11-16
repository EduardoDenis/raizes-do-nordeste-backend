package br.com.raizesdonordeste.backend.application.usuario;

import br.com.raizesdonordeste.backend.domain.usuario.Usuario;
import br.com.raizesdonordeste.backend.infrastructure.usuario.UsuarioRepository;
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