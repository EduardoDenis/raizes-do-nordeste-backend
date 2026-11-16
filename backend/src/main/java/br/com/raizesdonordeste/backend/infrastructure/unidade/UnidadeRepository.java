package br.com.raizesdonordeste.backend.infrastructure.unidade;

import br.com.raizesdonordeste.backend.domain.unidade.Unidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends CrudRepository<Unidade, Long> {

}