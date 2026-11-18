package br.com.raizesdonordeste.backend.api.itemPedido;

import br.com.raizesdonordeste.backend.domain.itemPedido.ItemPedido;
import br.com.raizesdonordeste.backend.application.itemPedido.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

    private final CriarItemPedidoUseCase criarItemPedidoUseCase;
    private final ListarItensPedidoUseCase listarItensPedidoUseCase;
    private final BuscarItemPedidoPorIdUseCase buscarItemPedidoPorIdUseCase;
    private final AtualizarItemPedidoUseCase atualizarItemPedidoUseCase;
    private final DeletarItemPedidoUseCase deletarItemPedidoUseCase;

    public ItemPedidoController(CriarItemPedidoUseCase criarItemPedidoUseCase,
                                ListarItensPedidoUseCase listarItensPedidoUseCase,
                                BuscarItemPedidoPorIdUseCase buscarItemPedidoPorIdUseCase,
                                AtualizarItemPedidoUseCase atualizarItemPedidoUseCase,
                                DeletarItemPedidoUseCase deletarItemPedidoUseCase) {
        this.criarItemPedidoUseCase = criarItemPedidoUseCase;
        this.listarItensPedidoUseCase = listarItensPedidoUseCase;
        this.buscarItemPedidoPorIdUseCase = buscarItemPedidoPorIdUseCase;
        this.atualizarItemPedidoUseCase = atualizarItemPedidoUseCase;
        this.deletarItemPedidoUseCase = deletarItemPedidoUseCase;
    }

    @PostMapping
    public ResponseEntity<ItemPedido> criarItemPedido(@RequestBody ItemPedido itemPedido) {
        return ResponseEntity.ok(criarItemPedidoUseCase.executar(itemPedido));
    }

    @GetMapping
    public ResponseEntity<Iterable<ItemPedido>> listarItens() {
        return ResponseEntity.ok(listarItensPedidoUseCase.executar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> buscarItemPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarItemPedidoPorIdUseCase.executar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedido> atualizarItem(@PathVariable Long id, @RequestBody ItemPedido itemPedido) {
        return ResponseEntity.ok(atualizarItemPedidoUseCase.executar(id, itemPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        deletarItemPedidoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}