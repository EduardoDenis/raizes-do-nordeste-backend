package br.com.raizesdonordeste.backend.infrastructure.produto;


import br.com.raizesdonordeste.backend.domain.produto.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
