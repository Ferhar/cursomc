package com.fernandohar.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandohar.cursomc.domain.Pedido;
import com.fernandohar.cursomc.repositories.PedidoRepository;
import com.fernandohar.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Obejto nao encontrado!ID: " +id
					 + ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
}
