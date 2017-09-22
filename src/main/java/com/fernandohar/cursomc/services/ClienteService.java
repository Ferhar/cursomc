package com.fernandohar.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandohar.cursomc.domain.Cliente;
import com.fernandohar.cursomc.repositories.ClienteRepository;
import com.fernandohar.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Cliente obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Obejto nao encontrado!ID: " +id
					 + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}
}
