package com.fernandohar.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandohar.cursomc.domain.Categoria;
import com.fernandohar.cursomc.repositories.CategoriaRepository;
import com.fernandohar.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Obejto nao encontrado!ID: " +id
					 + ", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}
}
