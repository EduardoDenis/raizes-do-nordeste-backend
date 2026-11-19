package br.com.raizesdonordeste.backend.application.estoque;
import br.com.raizesdonordeste.backend.infrastructure.estoque.EstoqueRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarEstoqueUseCase {
    private final EstoqueRepository repository;
    public DeletarEstoqueUseCase(EstoqueRepository repository) { this.repository = repository; }
    public void executar(Long id) { repository.deleteById(id); }
}