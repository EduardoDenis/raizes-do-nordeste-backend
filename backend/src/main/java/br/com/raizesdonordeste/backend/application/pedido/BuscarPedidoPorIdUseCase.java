package br.com.raizesdonordeste.backend.application.pedido;

import br.com.raizesdonordeste.backend.domain.pedido.Pedido;
import br.com.raizesdonordeste.backend.infrastructure.pedido.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarPedidoPorIdUseCase {
    private final PedidoRepository repository;

    public BuscarPedidoPorIdUseCase(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido executar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));
    }
}