package br.com.raizesdonordeste.backend.api;

import br.com.raizesdonordeste.backend.domain.Usuario;
import br.com.raizesdonordeste.backend.application.CriarUsuarioUseCase;
import br.com.raizesdonordeste.backend.infrastructure.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final UsuarioRepository repository;

    public UsuarioController(CriarUsuarioUseCase criarUsuarioUseCase, UsuarioRepository repository) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = criarUsuarioUseCase.executar(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @GetMapping
    public ResponseEntity<Iterable<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(repository.findAll());
    }
}