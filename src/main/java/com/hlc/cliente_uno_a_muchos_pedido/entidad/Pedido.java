package com.hlc.cliente_uno_a_muchos_pedido.entidad;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Table(name="pedidos")
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Past
	private LocalDateTime fecha;

	@NotBlank
	@NotNull
	private String descripcion;

	@NotNull
	private Integer cantidad;

	@JoinColumn(name = "cliente_id", nullable = false)
	@ManyToOne
	private Cliente cliente;

	@ManyToMany
	@JoinTable(
			name = "pedido_producto", // Nombre de la tabla intermedia
			joinColumns = @JoinColumn(name = "pedido_id"),  // Clave foránea de Pedido
			inverseJoinColumns = @JoinColumn(name = "producto_id") // Clave foránea de Producto
	)
	private List<Producto> productos;

	public Pedido(Long id, @Past LocalDateTime fecha, @NotBlank @NotNull String descripcion, @NotNull Integer cantidad,
				  Cliente cliente) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.cliente = cliente;
//		this.productos = productos;
	}

	public Pedido() {

	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}
