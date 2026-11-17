package br.com.raizesdonordeste.backend.application.pedido;

import br.com.raizesdonordeste.backend.domain.pedido.Pedido;
import br.com.raizesdonordeste.backend.infrastructure.pedido.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarPedidoUseCase {

    private final PedidoRepository repository;

    public CriarPedidoUseCase(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido executar(Pedido pedido) {
        // Como o valor total não pode ficar nulo, e o pedido começa vazio, garantimos aqui:
        if (pedido.getValorTotal() == null) {
            pedido.setValorTotal(java.math.BigDecimal.ZERO);
        }
        return repository.save(pedido);
    }
}