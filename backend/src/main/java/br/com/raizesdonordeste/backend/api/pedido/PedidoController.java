package br.com.raizesdonordeste.backend.api.pedido;

import br.com.raizesdonordeste.backend.domain.pedido.Pedido;
import br.com.raizesdonordeste.backend.application.pedido.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final ListarPedidosUseCase listarPedidosUseCase;
    private final BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase;
    private final AtualizarPedidoUseCase atualizarPedidoUseCase;
    private final DeletarPedidoUseCase deletarPedidoUseCase;

    public PedidoController(CriarPedidoUseCase criarPedidoUseCase,
                            ListarPedidosUseCase listarPedidosUseCase,
                            BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase,
                            AtualizarPedidoUseCase atualizarPedidoUseCase,
                            DeletarPedidoUseCase deletarPedidoUseCase) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.listarPedidosUseCase = listarPedidosUseCase;
        this.buscarPedidoPorIdUseCase = buscarPedidoPorIdUseCase;
        this.atualizarPedidoUseCase = atualizarPedidoUseCase;
        this.deletarPedidoUseCase = deletarPedidoUseCase;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(criarPedidoUseCase.executar(pedido));
    }

    @GetMapping
    public ResponseEntity<Iterable<Pedido>> listarPedidos() {
        return ResponseEntity.ok(listarPedidosUseCase.executar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarPedidoPorIdUseCase.executar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return ResponseEntity.ok(atualizarPedidoUseCase.executar(id, pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        deletarPedidoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}