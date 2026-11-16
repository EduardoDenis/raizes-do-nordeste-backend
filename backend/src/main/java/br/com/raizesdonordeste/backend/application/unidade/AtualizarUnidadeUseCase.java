package br.com.raizesdonordeste.backend.application.unidade;

import br.com.raizesdonordeste.backend.domain.unidade.Unidade;
import br.com.raizesdonordeste.backend.infrastructure.unidade.UnidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUnidadeUseCase {
    private final UnidadeRepository repository;

    public AtualizarUnidadeUseCase(UnidadeRepository repository) {
        this.repository = repository;
    }

    public Unidade executar(Long id, Unidade dadosAtualizados) {
        return repository.findById(id).map(unidadeExistente -> {
            unidadeExistente.setNome(dadosAtualizados.getNome());
            unidadeExistente.setCnpj(dadosAtualizados.getCnpj());
            unidadeExistente.setEndereco(dadosAtualizados.getEndereco());
            unidadeExistente.setAtiva(dadosAtualizados.isAtiva());
            return repository.save(unidadeExistente);
        }).orElseThrow(() -> new RuntimeException("Unidade não encontrada com ID: " + id));
    }
}