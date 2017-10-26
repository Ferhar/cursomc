package com.fernandohar.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandohar.cursomc.domain.ItemPedido;
import com.fernandohar.cursomc.domain.PagamentoComBoleto;
import com.fernandohar.cursomc.domain.Pedido;
import com.fernandohar.cursomc.domain.enums.EstadoPagamento;
import com.fernandohar.cursomc.repositories.ItemPedidoRepository;
import com.fernandohar.cursomc.repositories.PagamentoRepository;
import com.fernandohar.cursomc.repositories.PedidoRepository;
import com.fernandohar.cursomc.repositories.ProdutoRepository;
import com.fernandohar.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	 private BoletoService boletoService;
	 	
	 @Autowired
	 private PagamentoRepository pagamentoRepository;
	 
	 @Autowired
	 private ProdutoRepository produtoRepository;
	 
	 @Autowired
	 private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
	
	public Pedido update(Pedido obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public Pedido insert(Pedido obj) {
		 obj.setId(null);
		 obj.setInstante(new Date());
		 obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		 obj.getPagamento().setPedido(obj);
		 if (obj.getPagamento() instanceof PagamentoComBoleto) {
			 PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
		 	boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		 }
		 	obj = repo.save(obj);
		 	pagamentoRepository.save(obj.getPagamento());
		 	for (ItemPedido ip : obj.getItens()) {
		 		ip.setDesconto(0.0);
		 		ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
		 		ip.setPedido(obj);
		 	}
		 	itemPedidoRepository.save(obj.getItens());
		 	return obj;
		 }

}
