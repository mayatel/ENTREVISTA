package com.example.demo.Controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Modelo.Producto;
import com.example.demo.Servicios.ProductoServicio;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private ProductoServicio productoService;

	@GetMapping("/listar")
	public List<Producto> listarProductos() {
		return productoService.listar();
	}

	@GetMapping("buscar/{id}")
	public ResponseEntity<Producto> obtenerProductoPorId (@PathVariable Integer id) {
		Optional<Producto> producto = productoService.buscarPorId(id);
		if (producto.isPresent()) {
			return ResponseEntity.ok(producto.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/guardar")
	public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
		productoService.guardar(producto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarProducto( @RequestBody Producto producto) {
		if (productoService.existePorId(producto.getIdProducto())) {
			System.out.print(producto); 
			productoService.guardar(producto);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {
		if (productoService.existePorId(id)) {
			productoService.eliminarPorId(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
