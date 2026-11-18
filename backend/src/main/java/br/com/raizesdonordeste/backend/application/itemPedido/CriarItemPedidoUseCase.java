package br.com.raizesdonordeste.backend.application.itemPedido;
import br.com.raizesdonordeste.backend.domain.itemPedido.ItemPedido;
import br.com.raizesdonordeste.backend.infrastructure.itemPedido.ItemPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarItemPedidoUseCase {
    private final ItemPedidoRepository repository;
    public CriarItemPedidoUseCase(ItemPedidoRepository repository) { this.repository = repository; }
    public ItemPedido executar(ItemPedido item) { return repository.save(item); }
}