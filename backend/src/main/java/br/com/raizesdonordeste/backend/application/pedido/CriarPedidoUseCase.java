package br.com.raizesdonordeste.backend.application.pedido;

import br.com.raizesdonordeste.backend.api.pedido.dto.ItemPedidoRequestDTO;
import br.com.raizesdonordeste.backend.api.pedido.dto.PedidoRequestDTO;
import br.com.raizesdonordeste.backend.domain.estoque.Estoque;
import br.com.raizesdonordeste.backend.domain.exception.RecursoNaoEncontradoException;
import br.com.raizesdonordeste.backend.domain.exception.RegraNegocioException;
import br.com.raizesdonordeste.backend.domain.itemPedido.ItemPedido;
import br.com.raizesdonordeste.backend.domain.pedido.Pedido;
import br.com.raizesdonordeste.backend.domain.produto.Produto;
import br.com.raizesdonordeste.backend.domain.unidade.Unidade;
import br.com.raizesdonordeste.backend.domain.usuario.Usuario;
import br.com.raizesdonordeste.backend.infrastructure.estoque.EstoqueRepository;
import br.com.raizesdonordeste.backend.infrastructure.itemPedido.ItemPedidoRepository;
import br.com.raizesdonordeste.backend.infrastructure.pedido.PedidoRepository;
import br.com.raizesdonordeste.backend.infrastructure.produto.ProdutoRepository;
import br.com.raizesdonordeste.backend.infrastructure.unidade.UnidadeRepository;
import br.com.raizesdonordeste.backend.infrastructure.usuario.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CriarPedidoUseCase {

    private static final Logger log = LoggerFactory.getLogger(CriarPedidoUseCase.class);
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    private final UnidadeRepository unidadeRepository;
    private final UsuarioRepository usuarioRepository;

    public CriarPedidoUseCase(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository,
                              ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository,
                              UnidadeRepository unidadeRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
        this.unidadeRepository = unidadeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Pedido executar(PedidoRequestDTO dto) {
        if (dto.getCanalPedido() == null || dto.getCanalPedido().trim().isEmpty()) {
            throw new RegraNegocioException("CANAL_OBRIGATORIO", "O preenchimento do canalPedido é obrigatório (Ex: APP, TOTEM, BALCAO).");
        }

        Unidade unidade = unidadeRepository.findById(dto.getUnidadeId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Unidade não encontrada."));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        Pedido pedido = new Pedido();
        pedido.setUnidade(unidade);
        pedido.setUsuario(usuario);
        pedido.setCanalPedido(br.com.raizesdonordeste.backend.domain.pedido.CanalPedido.valueOf(dto.getCanalPedido().toUpperCase()));
        pedido.setStatus(br.com.raizesdonordeste.backend.domain.pedido.StatusPedido.valueOf("AGUARDANDO_PAGAMENTO"));
        pedido.setValorTotal(BigDecimal.ZERO);

        pedido = pedidoRepository.save(pedido);
        log.info("Pedido criado no canal {} com o ID: {}", pedido.getCanalPedido(), pedido.getId());

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedidoRequestDTO itemDto : dto.getItens()) {
            Produto produto = produtoRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Produto ID " + itemDto.getProdutoId() + " não encontrado."));

            Estoque estoque = estoqueRepository.findAll().spliterator().getExactSizeIfKnown() > 0 ?
                    buscarEstoque(unidade, produto) : null;

            if (estoque == null) {
                throw new RegraNegocioException("ESTOQUE_NAO_CONFIGURADO", "Estoque não configurado para o produto: " + produto.getNome());
            }

            if (estoque.getQuantidade() < itemDto.getQuantidade()) {
                throw new RegraNegocioException("ESTOQUE_INSUFICIENTE", "Não há quantidade suficiente para o produto: " + produto.getNome() + ". Disponível: " + estoque.getQuantidade());
            }

            estoque.setQuantidade(estoque.getQuantidade() - itemDto.getQuantidade());
            estoqueRepository.save(estoque);

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDto.getQuantidade());
            itemPedido.setPrecoUnitario(produto.getPrecoBase());
            itemPedidoRepository.save(itemPedido);

            BigDecimal subtotal = produto.getPrecoBase().multiply(new BigDecimal(itemDto.getQuantidade()));
            valorTotal = valorTotal.add(subtotal);
        }

        pedido.setValorTotal(valorTotal);
        return pedidoRepository.save(pedido);
    }

    private Estoque buscarEstoque(Unidade unidade, Produto produto) {
        for (Estoque est : estoqueRepository.findAll()) {
            if (est.getUnidade().getId().equals(unidade.getId()) && est.getProduto().getId().equals(produto.getId())) {
                return est;
            }
        }
        return null;
    }
}