package com.hlc.cliente_uno_a_muchos_pedido.repositorio;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
