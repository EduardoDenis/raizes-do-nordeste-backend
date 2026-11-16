package br.com.raizesdonordeste.backend.application.unidade;

import br.com.raizesdonordeste.backend.infrastructure.unidade.UnidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarUnidadeUseCase {
    private final UnidadeRepository repository;

    public DeletarUnidadeUseCase(UnidadeRepository repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        repository.deleteById(id);
    }
}