package br.com.raizesdonordeste.backend.application.usuario;

import br.com.raizesdonordeste.backend.domain.usuario.Usuario;
import br.com.raizesdonordeste.backend.infrastructure.usuario.UsuarioRepository;
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