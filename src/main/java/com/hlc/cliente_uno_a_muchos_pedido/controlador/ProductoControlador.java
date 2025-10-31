package com.hlc.cliente_uno_a_muchos_pedido.controlador;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Categoria;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Pedido;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;
import com.hlc.cliente_uno_a_muchos_pedido.servicio.CategoriaServicio;
import com.hlc.cliente_uno_a_muchos_pedido.servicio.ClienteServicio;
import com.hlc.cliente_uno_a_muchos_pedido.servicio.PedidoServicio;
import com.hlc.cliente_uno_a_muchos_pedido.servicio.ProductoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoControlador {
    private static final String LISTA_CATEGORIA = "categoria/categoria";


    @Autowired
    private CategoriaServicio categoriaServicio;
    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private PedidoServicio pedidoServicio;


    @PostMapping("/{id}/categorias")
    public String mostrarCategoriasPedido(@PathVariable Long id,@RequestParam("pedidoId") Long pedidoId, Model model) {
        Producto producto = productoServicio.obtenerProductoPorId(id);
        Categoria categoria = producto.getCategoria();
        model.addAttribute("categoria", categoria);
        model.addAttribute("producto", producto);
        model.addAttribute("pedidoId", pedidoId);
        return LISTA_CATEGORIA;
    }
}

