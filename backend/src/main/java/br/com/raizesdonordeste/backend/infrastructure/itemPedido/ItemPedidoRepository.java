package br.com.raizesdonordeste.backend.infrastructure.itemPedido;

import br.com.raizesdonordeste.backend.domain.itemPedido.ItemPedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends CrudRepository<ItemPedido, Long> {
}