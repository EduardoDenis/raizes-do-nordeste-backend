package br.com.raizesdonordeste.backend.application.pedido;

import br.com.raizesdonordeste.backend.domain.pedido.CanalPedido;
import br.com.raizesdonordeste.backend.domain.pedido.Pedido;
import br.com.raizesdonordeste.backend.infrastructure.pedido.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class ListarPedidosUseCase {
    private final PedidoRepository repository;

    public ListarPedidosUseCase(PedidoRepository repository) {
        this.repository = repository;
    }

    public Iterable<Pedido> executar(CanalPedido canalPedido) {
        if (canalPedido != null) {
            return repository.findByCanalPedido(canalPedido);
        }
        return repository.findAll();
    }
}