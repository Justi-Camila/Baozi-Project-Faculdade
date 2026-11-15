package com.store.baozi.repository;

import com.store.baozi.model.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    boolean existsByClienteId(Long id);
    boolean existsByProdutoId(Long id);

}
