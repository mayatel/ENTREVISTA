package com.example.demo.Producto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Modelo.Producto;
import com.example.demo.Repositorio.ProductoRepository;
import com.example.demo.Servicios.ProductoServicio;

public class ProductoServiceTest {

	@Mock
	private ProductoRepository productoRepository;

	@InjectMocks
	private ProductoServicio productoService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void listarProductosTest() {
		// Simula una lista de productos
		List<Producto> productos = List.of(new Producto(1, "Producto 1", 1, 1.0), new Producto(2, "Producto 2", 2, 10.0));

		// Cuando se llame a findAll() en el repositorio, retorna la lista de productos
		// simulada
		when(productoRepository.findAll()).thenReturn(productos);

		// Llama al método del servicio que quieres probar
		List<Producto> resultado = productoService.listar();

		// Verifica que el resultado sea el esperado
		assertEquals(2, resultado.size());
	}

	@Test
	public void obtenerProductoPorIdTest() {
		// Simula un producto
		Producto producto = new Producto(1, "Producto 1", 19,10.0);

		// Cuando se llame a findById() en el repositorio, retorna el producto simulado
		when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

		// Llama al método del servicio que quieres probar
		Optional<Producto> resultado = productoService.buscarPorId(1);

		// Verifica que el resultado sea el esperado
		assertTrue(resultado.isPresent());
		assertEquals("Producto 1", resultado.get().getNombre());
	}

	// Agrega más pruebas para otros métodos del servicio aquí

	/*@Test
	public void crearProductoTest() {
		 
		// Simula un producto nuevo
		Producto nuevoProducto = new Producto();
		Integer idProducto = 10;
		String nombre = "nuevo";
		int cantidad = 10;

		// Cuando se llame a save() en el repositorio, retorna el producto simulado
		when(productoRepository.save(nuevoProducto)).thenReturn(nuevoProducto);
		 
		nuevoProducto.setIdProducto(idProducto);
		nuevoProducto.setCantidad(cantidad);
		nuevoProducto.setNombre(nombre);

		// Verifica que el resultado sea el esperado
		assertEquals("Nuevo Producto", nuevoProducto.getNombre());
		assertEquals(10, nuevoProducto.getCantidad());
	}
*/
	@Test
	public void actualizarProductoTest() {
		// Simula un producto existente
		Producto productoExistente = new Producto(1, "Producto Existente", 20,10.0);

		// Cuando se llame a findById() en el repositorio, retorna el producto simulado
		when(productoRepository.findById(1)).thenReturn(Optional.of(productoExistente));

		// Simula un producto actualizado
		Producto productoActualizado = new Producto(1, "Producto Modificado", 30, 10.0);

		// Cuando se llame a save() en el repositorio, retorna el producto actualizado
		// simulado
		when(productoRepository.save(productoActualizado)).thenReturn(productoActualizado);

		// Llama al método del servicio que quieres probar
		Producto productoResultado = productoService.guardar(productoActualizado);

		// Verifica que el resultado sea el esperado
		assertEquals("Producto Modificado", productoResultado.getNombre());
		assertEquals(30, productoResultado.getCantidad());
	}

	  @Test
	    public void eliminarProductoPorIdExistenteTest() {
	        // Simula un ID existente y un producto existente en la base de datos
	        Integer idExistente = 1;
	        Producto productoExistente = new Producto(idExistente, "Producto Existente", 10, 10.0);

	        // Cuando se llame a existePorId en el servicio, retorna true
	        when(productoService.existePorId(idExistente)).thenReturn(true);

	        // Cuando se llame a findById en el repositorio, retorna el producto simulado
	        when(productoRepository.findById(idExistente)).thenReturn(Optional.of(productoExistente));

	        // Llama al método del servicio que quieres probar
	        boolean resultado = productoService.eliminarPorId(idExistente);

	        // Verifica que el resultado sea true, lo que indica que se eliminó con éxito
	        assertTrue(resultado);

	        // Verifica que se haya llamado a deleteById en el repositorio con el ID correcto
	        verify(productoRepository).deleteById(idExistente);
	       // verify(productoRepository).deleteById(33);
	  }

	    @Test
	    public void eliminarProductoPorIdNoExistenteTest() {
	        // Simula un ID que no existe en la base de datos
	        Integer idNoExistente = 2;

	        // Cuando se llame a existePorId en el servicio, retorna false
	        when(productoService.existePorId(idNoExistente)).thenReturn(false);

	        // Llama al método del servicio que quieres probar
	        boolean resultado = productoService.eliminarPorId(idNoExistente);

	        // Verifica que el resultado sea false, lo que indica que no se eliminó
	        assertFalse(resultado);

	        // Verifica que no se haya llamado a deleteById en el repositorio
	        verify(productoRepository, never()).deleteById(idNoExistente);
	    }

}
