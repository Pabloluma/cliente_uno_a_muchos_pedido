package com.hlc.cliente_uno_a_muchos_pedido.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Categoria;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Cliente;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Pedido;
import com.hlc.cliente_uno_a_muchos_pedido.repositorio.*;

@Component
public class InicializadorDatos implements CommandLineRunner  {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;
	
	private Faker faker = new Faker();
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Producto> productos = new ArrayList<>();
        List<Categoria> categoriasLista = new ArrayList<>();

        Random randomCat = new Random();
        for (int i = 0; i < 4; i++) {
            Categoria categoria = new Categoria();
            categoria.setNombre(faker.book().title());
            categoria.setDescripcion(faker.book().title());
            categoriaRepository.save(categoria);
            categoriasLista.add(categoria);
        }

        // Crear 10 productos de prueba
        for (int k = 0; k < 10; k++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setPeso((float) faker.number().randomDouble(2, 1, 10));
            producto.setStock(faker.number().numberBetween(10, 100));
            int posCategoria = new Random().nextInt(categoriasLista.size());
            producto.setCategoria(categoriasLista.get(posCategoria));
            productoRepository.save(producto);
            productos.add(producto);
        }


        Random random = new Random();
        // Crear clientes con pedidos y asignar productos aleatoriamente
        for (int i = 0; i < 5; i++) {
            Cliente cliente = new Cliente();
            cliente.setNombre(faker.name().fullName());
            clienteRepository.save(cliente);

            for (int j = 0; j < 3; j++) {
                Pedido pedido = new Pedido();
                pedido.setFecha(LocalDateTime.now().minusDays(faker.number().numberBetween(1, 30)));
                pedido.setDescripcion(faker.commerce().productName());
                pedido.setCantidad(faker.number().numberBetween(1, 10));
                pedido.setCliente(cliente);
                int numeroProducto = random.nextInt(1,productos.size());
                List<Producto> listaProductoPedido = new ArrayList<>(productos.subList(0, numeroProducto));
                pedido.setProductos(listaProductoPedido);
                pedidoRepository.save(pedido);
            }
        }
    }

}
