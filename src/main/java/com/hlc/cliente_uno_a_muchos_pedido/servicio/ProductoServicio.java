package com.hlc.cliente_uno_a_muchos_pedido.servicio;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Cliente;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Pedido;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;

import java.util.List;

public interface ProductoServicio {
    Producto guardarProducto(Producto producto);

    Producto obtenerProductoPorId(Long id);

    List<Producto> obtenerTodosLosProductos();

    Producto actualizarProducto(Long id, Producto producto);

    void eliminarProducto(Long id);

    List<Producto> obtenerProductosPorPedido(Pedido Pedido);

}
