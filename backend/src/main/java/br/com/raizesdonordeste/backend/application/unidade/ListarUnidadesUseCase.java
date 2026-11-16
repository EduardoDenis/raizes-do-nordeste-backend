package br.com.raizesdonordeste.backend.application.unidade;

import br.com.raizesdonordeste.backend.domain.unidade.Unidade;
import br.com.raizesdonordeste.backend.infrastructure.unidade.UnidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class ListarUnidadesUseCase {
    private final UnidadeRepository repository;

    public ListarUnidadesUseCase(UnidadeRepository repository) {
        this.repository = repository;
    }

    public Iterable<Unidade> executar() {
        return repository.findAll();
    }
}