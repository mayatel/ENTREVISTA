package com.example.demo.Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 
	Integer idProducto;
	String nombre;
	int cantidad ;
	double precio;

	public Producto() {
		super();
	}
	public Producto(Integer idProducto, String nombre, int cantidad, double precio) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", cantidad=" + cantidad + "]";
	}
	
	 

}
