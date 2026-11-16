package br.com.raizesdonordeste.backend.api.usuario;

import br.com.raizesdonordeste.backend.application.usuario.*;
import br.com.raizesdonordeste.backend.domain.usuario.Usuario;
import br.com.raizesdonordeste.backend.infrastructure.usuario.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final UsuarioRepository repository;
    private final DeletarUsuarioUseCase deletarUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final ListarUsuariosUseCase listarUsuariosUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    public UsuarioController(CriarUsuarioUseCase criarUsuarioUseCase, UsuarioRepository repository,
                             DeletarUsuarioUseCase deletarUsuarioUseCase, AtualizarUsuarioUseCase atualizarUsuarioUseCase,
                             ListarUsuariosUseCase listarUsuariosUseCase, BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.repository = repository;
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;

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

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarUsuarioPorIdUseCase.executar(id));
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