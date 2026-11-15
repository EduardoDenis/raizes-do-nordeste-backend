package br.com.raizesdonordeste.backend.api;

import br.com.raizesdonordeste.backend.application.AtualizarUsuarioUseCase;
import br.com.raizesdonordeste.backend.application.DeletarUsuarioUseCase;
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
    private final DeletarUsuarioUseCase deletarUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    public UsuarioController(CriarUsuarioUseCase criarUsuarioUseCase, UsuarioRepository repository,
                             DeletarUsuarioUseCase deletarUsuarioUseCase, AtualizarUsuarioUseCase atualizarUsuarioUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.repository = repository;
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        deletarUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = atualizarUsuarioUseCase.executar(id, usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

}