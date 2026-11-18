package br.com.raizesdonordeste.backend.application.produto;

import br.com.raizesdonordeste.backend.domain.produto.Produto;
import br.com.raizesdonordeste.backend.infrastructure.produto.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarProdutoUseCase {

    private final ProdutoRepository repository;

    public CriarProdutoUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto executar(Produto produto) {
        return repository.save(produto);
    }


}
