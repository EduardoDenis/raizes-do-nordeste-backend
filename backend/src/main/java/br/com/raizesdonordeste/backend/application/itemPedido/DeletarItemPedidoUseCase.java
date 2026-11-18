package br.com.raizesdonordeste.backend.application.itemPedido;
import br.com.raizesdonordeste.backend.infrastructure.itemPedido.ItemPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarItemPedidoUseCase {
    private final ItemPedidoRepository repository;
    public DeletarItemPedidoUseCase(ItemPedidoRepository repository) { this.repository = repository; }
    public void executar(Long id) { repository.deleteById(id); }
}