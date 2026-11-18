package br.com.raizesdonordeste.backend.api.produto;

import br.com.raizesdonordeste.backend.domain.produto.Produto;
import br.com.raizesdonordeste.backend.application.produto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final CriarProdutoUseCase criarProdutoUseCase;
    private final ListarProdutosUseCase listarProdutosUseCase;
    private final BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase;
    private final AtualizarProdutoUseCase atualizarProdutoUseCase;
    private final DeletarProdutoUseCase deletarProdutoUseCase;

    public ProdutoController(CriarProdutoUseCase criarProdutoUseCase,
                             ListarProdutosUseCase listarProdutosUseCase,
                             BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase,
                             AtualizarProdutoUseCase atualizarProdutoUseCase,
                             DeletarProdutoUseCase deletarProdutoUseCase) {
        this.criarProdutoUseCase = criarProdutoUseCase;
        this.listarProdutosUseCase = listarProdutosUseCase;
        this.buscarProdutoPorIdUseCase = buscarProdutoPorIdUseCase;
        this.atualizarProdutoUseCase = atualizarProdutoUseCase;
        this.deletarProdutoUseCase = deletarProdutoUseCase;
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        return ResponseEntity.ok(criarProdutoUseCase.executar(produto));
    }

    @GetMapping
    public ResponseEntity<Iterable<Produto>> listarProdutos() {
        return ResponseEntity.ok(listarProdutosUseCase.executar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarProdutoPorIdUseCase.executar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        return ResponseEntity.ok(atualizarProdutoUseCase.executar(id, produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        deletarProdutoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}