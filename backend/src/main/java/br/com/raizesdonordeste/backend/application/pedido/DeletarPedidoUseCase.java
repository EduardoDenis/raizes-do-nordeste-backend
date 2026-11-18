package br.com.raizesdonordeste.backend.application.pedido;

import br.com.raizesdonordeste.backend.infrastructure.pedido.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarPedidoUseCase {
    private final PedidoRepository repository;

    public DeletarPedidoUseCase(PedidoRepository repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        repository.deleteById(id);
    }
}