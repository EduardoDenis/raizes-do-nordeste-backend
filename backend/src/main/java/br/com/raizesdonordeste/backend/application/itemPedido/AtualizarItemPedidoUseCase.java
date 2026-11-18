package br.com.raizesdonordeste.backend.application.itemPedido;
import br.com.raizesdonordeste.backend.domain.itemPedido.ItemPedido;
import br.com.raizesdonordeste.backend.infrastructure.itemPedido.ItemPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarItemPedidoUseCase {
    private final ItemPedidoRepository repository;
    public AtualizarItemPedidoUseCase(ItemPedidoRepository repository) { this.repository = repository; }
    public ItemPedido executar(Long id, ItemPedido dadosAtualizados) {
        return repository.findById(id).map(item -> {
            item.setQuantidade(dadosAtualizados.getQuantidade());
            item.setPrecoUnitario(dadosAtualizados.getPrecoUnitario());
            return repository.save(item);
        }).orElseThrow(() -> new RuntimeException("Item não encontrado com ID: " + id));
    }
}