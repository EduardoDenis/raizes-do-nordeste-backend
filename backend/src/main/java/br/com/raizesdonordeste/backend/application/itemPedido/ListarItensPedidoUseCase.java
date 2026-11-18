package br.com.raizesdonordeste.backend.application.itemPedido;
import br.com.raizesdonordeste.backend.domain.itemPedido.ItemPedido;
import br.com.raizesdonordeste.backend.infrastructure.itemPedido.ItemPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class ListarItensPedidoUseCase {
    private final ItemPedidoRepository repository;
    public ListarItensPedidoUseCase(ItemPedidoRepository repository) { this.repository = repository; }
    public Iterable<ItemPedido> executar() { return repository.findAll(); }
}