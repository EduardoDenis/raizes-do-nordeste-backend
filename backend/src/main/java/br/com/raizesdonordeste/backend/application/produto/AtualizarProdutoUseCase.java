package br.com.raizesdonordeste.backend.application.produto;

import br.com.raizesdonordeste.backend.domain.produto.Produto;
import br.com.raizesdonordeste.backend.infrastructure.produto.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarProdutoUseCase {
    private final ProdutoRepository repository;

    public AtualizarProdutoUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto executar(Long id, Produto dadosAtualizados) {
        return repository.findById(id).map(produtoExistente -> {
            produtoExistente.setNome(dadosAtualizados.getNome());
            produtoExistente.setPrecoBase(dadosAtualizados.getPrecoBase());
            return repository.save(produtoExistente);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }
}