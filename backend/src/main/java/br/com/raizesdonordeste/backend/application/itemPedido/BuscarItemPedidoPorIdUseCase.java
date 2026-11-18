package br.com.raizesdonordeste.backend.application.itemPedido;
import br.com.raizesdonordeste.backend.domain.itemPedido.ItemPedido;
import br.com.raizesdonordeste.backend.infrastructure.itemPedido.ItemPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarItemPedidoPorIdUseCase {
    private final ItemPedidoRepository repository;
    public BuscarItemPedidoPorIdUseCase(ItemPedidoRepository repository) { this.repository = repository; }
    public ItemPedido executar(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado com ID: " + id));
    }
}