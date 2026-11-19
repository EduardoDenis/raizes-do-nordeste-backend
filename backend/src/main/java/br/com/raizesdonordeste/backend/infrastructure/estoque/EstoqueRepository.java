package br.com.raizesdonordeste.backend.infrastructure.estoque;

import br.com.raizesdonordeste.backend.domain.estoque.Estoque;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends CrudRepository<Estoque, Long> {
}