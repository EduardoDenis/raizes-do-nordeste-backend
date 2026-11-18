package br.com.raizesdonordeste.backend.application.pedido;

import br.com.raizesdonordeste.backend.domain.pedido.Pedido;
import br.com.raizesdonordeste.backend.infrastructure.pedido.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarPedidoUseCase {
    private final PedidoRepository repository;

    public AtualizarPedidoUseCase(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido executar(Long id, Pedido dadosAtualizados) {
        return repository.findById(id).map(pedidoExistente -> {
            pedidoExistente.setStatus(dadosAtualizados.getStatus());
            pedidoExistente.setCanalPedido(dadosAtualizados.getCanalPedido());
            pedidoExistente.setValorTotal(dadosAtualizados.getValorTotal());
            return repository.save(pedidoExistente);
        }).orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));
    }
}