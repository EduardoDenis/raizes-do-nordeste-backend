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
    private final ProcessarPagamentoMockUseCase processarPagamentoMockUseCase;

    public PedidoController(CriarPedidoUseCase criarPedidoUseCase,
                            ListarPedidosUseCase listarPedidosUseCase,
                            BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase,
                            AtualizarPedidoUseCase atualizarPedidoUseCase,
                            DeletarPedidoUseCase deletarPedidoUseCase,
    ProcessarPagamentoMockUseCase processarPagamentoMockUseCase) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.listarPedidosUseCase = listarPedidosUseCase;
        this.buscarPedidoPorIdUseCase = buscarPedidoPorIdUseCase;
        this.atualizarPedidoUseCase = atualizarPedidoUseCase;
        this.deletarPedidoUseCase = deletarPedidoUseCase;
        this.processarPagamentoMockUseCase = processarPagamentoMockUseCase;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody br.com.raizesdonordeste.backend.api.pedido.dto.PedidoRequestDTO dto) {
        return ResponseEntity.status(201).body(criarPedidoUseCase.executar(dto));
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

    @PutMapping("/{id}/pagamento")
    public ResponseEntity<Pedido> processarPagamento(@PathVariable Long id, @RequestBody br.com.raizesdonordeste.backend.api.pedido.dto.PagamentoRequestDTO dto) {
        return ResponseEntity.ok(processarPagamentoMockUseCase.executar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        deletarPedidoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}