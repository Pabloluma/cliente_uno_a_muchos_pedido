package com.hlc.cliente_uno_a_muchos_pedido.servicio;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Categoria;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Cliente;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Pedido;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;

import java.util.List;

public interface CategoriaServicio {
    Categoria guardarCategoria(Categoria categoria);
    Categoria obtenerCategoriaPorId(Long id);
    List<Categoria> obtenerTodosLosCategorias();
    Categoria actualizarCategoria(Long id, Categoria categoria);
    void eliminarCategoria(Long id);
    List<Producto>obtenerProductosPorCategoria(Categoria categoria);
}
