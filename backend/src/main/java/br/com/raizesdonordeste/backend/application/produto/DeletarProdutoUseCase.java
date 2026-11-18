package br.com.raizesdonordeste.backend.application.produto;


import br.com.raizesdonordeste.backend.infrastructure.produto.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarProdutoUseCase {
    private final ProdutoRepository repository;

    public DeletarProdutoUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        repository.deleteById(id);
    }
}