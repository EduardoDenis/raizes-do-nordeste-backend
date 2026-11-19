package br.com.raizesdonordeste.backend.api.estoque;

import br.com.raizesdonordeste.backend.domain.estoque.Estoque;
import br.com.raizesdonordeste.backend.application.estoque.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final CriarEstoqueUseCase criarEstoqueUseCase;
    private final ListarEstoquesUseCase listarEstoquesUseCase;
    private final BuscarEstoquePorIdUseCase buscarEstoquePorIdUseCase;
    private final AtualizarEstoqueUseCase atualizarEstoqueUseCase;
    private final DeletarEstoqueUseCase deletarEstoqueUseCase;

    public EstoqueController(CriarEstoqueUseCase criarEstoqueUseCase,
                             ListarEstoquesUseCase listarEstoquesUseCase,
                             BuscarEstoquePorIdUseCase buscarEstoquePorIdUseCase,
                             AtualizarEstoqueUseCase atualizarEstoqueUseCase,
                             DeletarEstoqueUseCase deletarEstoqueUseCase) {
        this.criarEstoqueUseCase = criarEstoqueUseCase;
        this.listarEstoquesUseCase = listarEstoquesUseCase;
        this.buscarEstoquePorIdUseCase = buscarEstoquePorIdUseCase;
        this.atualizarEstoqueUseCase = atualizarEstoqueUseCase;
        this.deletarEstoqueUseCase = deletarEstoqueUseCase;
    }

    @PostMapping
    public ResponseEntity<Estoque> criarEstoque(@RequestBody Estoque estoque) {
        return ResponseEntity.ok(criarEstoqueUseCase.executar(estoque));
    }

    @GetMapping
    public ResponseEntity<Iterable<Estoque>> listarEstoques() {
        return ResponseEntity.ok(listarEstoquesUseCase.executar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarEstoquePorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarEstoquePorIdUseCase.executar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> atualizarEstoque(@PathVariable Long id, @RequestBody Estoque estoque) {
        return ResponseEntity.ok(atualizarEstoqueUseCase.executar(id, estoque));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id) {
        deletarEstoqueUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}