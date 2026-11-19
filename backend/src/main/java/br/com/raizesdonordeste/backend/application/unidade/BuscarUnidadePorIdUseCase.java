package br.com.raizesdonordeste.backend.application.unidade;

import br.com.raizesdonordeste.backend.domain.exception.RecursoNaoEncontradoException;
import br.com.raizesdonordeste.backend.domain.unidade.Unidade;
import br.com.raizesdonordeste.backend.infrastructure.unidade.UnidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarUnidadePorIdUseCase {
    private final UnidadeRepository repository;

    public BuscarUnidadePorIdUseCase(UnidadeRepository repository) {
        this.repository = repository;
    }

    public Unidade executar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Unidade não encontrado com ID: " + id));
    }
}