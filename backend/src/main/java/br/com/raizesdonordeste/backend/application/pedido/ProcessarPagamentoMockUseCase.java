package br.com.raizesdonordeste.backend.application.pedido;

import br.com.raizesdonordeste.backend.api.pedido.dto.PagamentoRequestDTO;
import br.com.raizesdonordeste.backend.domain.exception.RecursoNaoEncontradoException;
import br.com.raizesdonordeste.backend.domain.exception.RegraNegocioException;
import br.com.raizesdonordeste.backend.domain.pedido.Pedido;
import br.com.raizesdonordeste.backend.infrastructure.pedido.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProcessarPagamentoMockUseCase {

    private final PedidoRepository pedidoRepository;

    public ProcessarPagamentoMockUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido executar(Long pedidoId, PagamentoRequestDTO dto) {
        // 1. Busca o pedido
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado com ID: " + pedidoId));

        // 2. Garante que só pagamos pedidos que estão aguardando
        if (!pedido.getStatus().name().equals("AGUARDANDO_PAGAMENTO")) {
            throw new RegraNegocioException("STATUS_INVALIDO", "Este pedido já foi processado ou cancelado.");
        }

        // 3. Simula a resposta do Gateway de Pagamento
        if ("APROVADO".equalsIgnoreCase(dto.getStatusPagamento())) {
            pedido.setStatus(br.com.raizesdonordeste.backend.domain.pedido.StatusPedido.valueOf("PAGO"));
        } else if ("RECUSADO".equalsIgnoreCase(dto.getStatusPagamento())) {
            pedido.setStatus(br.com.raizesdonordeste.backend.domain.pedido.StatusPedido.valueOf("CANCELADO"));
            // (Num cenário real avançado, devolveríamos o estoque aqui)
        } else {
            throw new RegraNegocioException("PAGAMENTO_INVALIDO", "Envie 'APROVADO' ou 'RECUSADO'.");
        }

        return pedidoRepository.save(pedido);
    }
}