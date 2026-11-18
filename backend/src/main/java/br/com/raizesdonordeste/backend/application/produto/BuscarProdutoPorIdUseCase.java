package br.com.raizesdonordeste.backend.application.produto;

import br.com.raizesdonordeste.backend.domain.produto.Produto;
import br.com.raizesdonordeste.backend.infrastructure.produto.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarProdutoPorIdUseCase {
    private final ProdutoRepository repository;

    public BuscarProdutoPorIdUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto executar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }
}