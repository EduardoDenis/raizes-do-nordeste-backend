package br.com.raizesdonordeste.backend.infrastructure.pedido;

import br.com.raizesdonordeste.backend.domain.pedido.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}