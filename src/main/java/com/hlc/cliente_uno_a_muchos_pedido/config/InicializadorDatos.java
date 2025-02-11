package com.hlc.cliente_uno_a_muchos_pedido.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    public void run(String... args) throws Exception {
        List<Producto> productos = new ArrayList<>();

        // Crear 10 productos de prueba
        for (int k = 0; k < 10; k++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setPeso((float) faker.number().randomDouble(2, 1, 10)); // Peso entre 1 y 10 kg
            producto.setStock(faker.number().numberBetween(10, 100)); // Stock entre 10 y 100 unidades
            productoRepository.save(producto);
            productos.add(producto);
        }

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

                Collections.shuffle(productos);
                int cantidadProductos = faker.number().numberBetween(1, 4);
                List<Producto> productosPedido = new ArrayList<>(productos.subList(0, cantidadProductos));
                pedido.setProductos(productosPedido);
                pedidoRepository.save(pedido);
            }
        }
    }

}
