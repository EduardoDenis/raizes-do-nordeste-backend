package br.com.raizesdonordeste.backend.application.estoque;
import br.com.raizesdonordeste.backend.domain.estoque.Estoque;
import br.com.raizesdonordeste.backend.domain.exception.RecursoNaoEncontradoException;
import br.com.raizesdonordeste.backend.infrastructure.estoque.EstoqueRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarEstoquePorIdUseCase {
    private final EstoqueRepository repository;
    public BuscarEstoquePorIdUseCase(EstoqueRepository repository) { this.repository = repository; }
    public Estoque executar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estoque não encontrado com ID: " + id));
    }
}