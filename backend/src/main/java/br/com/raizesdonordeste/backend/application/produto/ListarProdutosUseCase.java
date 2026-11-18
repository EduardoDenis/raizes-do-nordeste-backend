package br.com.raizesdonordeste.backend.application.produto;

import br.com.raizesdonordeste.backend.domain.produto.Produto;
import br.com.raizesdonordeste.backend.infrastructure.produto.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ListarProdutosUseCase {
    private final ProdutoRepository repository;

    public ListarProdutosUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Iterable<Produto> executar() {
        return repository.findAll();
    }
}