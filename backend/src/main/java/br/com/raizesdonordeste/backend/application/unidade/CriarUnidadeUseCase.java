package br.com.raizesdonordeste.backend.application.unidade;

import br.com.raizesdonordeste.backend.domain.unidade.Unidade;
import br.com.raizesdonordeste.backend.infrastructure.unidade.UnidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarUnidadeUseCase {

    private final UnidadeRepository repository;

    public CriarUnidadeUseCase(UnidadeRepository repository) {
        this.repository = repository;
    }

    public Unidade executar(Unidade unidade) {
        return repository.save(unidade);
    }
}