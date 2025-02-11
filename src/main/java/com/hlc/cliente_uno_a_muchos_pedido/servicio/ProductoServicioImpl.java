package com.hlc.cliente_uno_a_muchos_pedido.servicio;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Pedido;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;
import com.hlc.cliente_uno_a_muchos_pedido.excepcion.RecursoNoEncontradoException;
import com.hlc.cliente_uno_a_muchos_pedido.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    @Autowired
    private ProductoRepository productoRepository;

    public ProductoServicioImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElseThrow(
                () -> new RecursoNoEncontradoException("Pedido no encontrado con ID: " + id));
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto actualizarProducto(Long id, Producto producto) {
        Producto existente = obtenerProductoPorId(id);
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        return productoRepository.save(existente);
    }

    @Override
    public void eliminarProducto(Long id) {
        Producto pedido = obtenerProductoPorId(id);
        productoRepository.delete(pedido);

    }

    @Override
    public List<Producto> obtenerProductosPorPedido(Pedido pedido) {
        return productoRepository.findByPedidos(pedido);
    }
}
