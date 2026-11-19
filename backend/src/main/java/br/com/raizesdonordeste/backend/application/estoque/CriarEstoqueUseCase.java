package br.com.raizesdonordeste.backend.application.estoque;
import br.com.raizesdonordeste.backend.domain.estoque.Estoque;
import br.com.raizesdonordeste.backend.infrastructure.estoque.EstoqueRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarEstoqueUseCase {
    private final EstoqueRepository repository;
    public CriarEstoqueUseCase(EstoqueRepository repository) { this.repository = repository; }
    public Estoque executar(Estoque estoque) { return repository.save(estoque); }
}