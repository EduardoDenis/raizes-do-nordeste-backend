package br.com.raizesdonordeste.backend.application.estoque;
import br.com.raizesdonordeste.backend.domain.estoque.Estoque;
import br.com.raizesdonordeste.backend.infrastructure.estoque.EstoqueRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarEstoqueUseCase {
    private final EstoqueRepository repository;
    public AtualizarEstoqueUseCase(EstoqueRepository repository) { this.repository = repository; }
    public Estoque executar(Long id, Estoque dadosAtualizados) {
        return repository.findById(id).map(estoque -> {
            estoque.setQuantidade(dadosAtualizados.getQuantidade());
            return repository.save(estoque);
        }).orElseThrow(() -> new RuntimeException("Estoque não encontrado com ID: " + id));
    }
}