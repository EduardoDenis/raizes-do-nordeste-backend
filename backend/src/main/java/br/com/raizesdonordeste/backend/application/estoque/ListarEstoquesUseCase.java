package br.com.raizesdonordeste.backend.application.estoque;
import br.com.raizesdonordeste.backend.domain.estoque.Estoque;
import br.com.raizesdonordeste.backend.infrastructure.estoque.EstoqueRepository;
import org.springframework.stereotype.Service;

@Service
public class ListarEstoquesUseCase {
    private final EstoqueRepository repository;
    public ListarEstoquesUseCase(EstoqueRepository repository) { this.repository = repository; }
    public Iterable<Estoque> executar() { return repository.findAll(); }
}