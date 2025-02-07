package com.hlc.cliente_uno_a_muchos_pedido.config;

import java.time.LocalDateTime;

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
	
	private Faker faker = new Faker();

	@Override
	public void run(String... args) throws Exception {
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
                pedidoRepository.save(pedido);
            }
        }
		
	}

}
