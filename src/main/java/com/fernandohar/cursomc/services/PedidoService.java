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
	
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
	
	public void insert(Pedido obj) {
		obj.setId(null);
		repo.save(obj);
	}
	
	public Pedido update(Pedido obj) {
		find(obj.getId());
		return repo.save(obj);
	}

}
