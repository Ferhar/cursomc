package com.fernandohar.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernandohar.cursomc.domain.Cliente;

@Repository 
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
}
