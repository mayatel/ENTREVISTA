package com.example.demo.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Modelo.Producto;
import com.example.demo.Repositorio.ProductoRepository;

@Service
public class ProductoServicio {
	@Autowired
	private ProductoRepository bdQuery;

	public List<Producto> listar() {
		return (List<Producto>) bdQuery.findAll();
	}

	public Optional<Producto> buscarPorId(Integer id) {
		return bdQuery.findById(id);

	}

	public Producto guardar(Producto obj) {
		return bdQuery.save(obj);
	}

	public boolean existePorId(Integer id) {
		return bdQuery.existsById(id);
	}

	public boolean eliminarPorId(Integer id) {
		 if(existePorId(id)){
			 bdQuery.deleteById(id); 
		 return true;
		 }
		 return false;

	}

}


