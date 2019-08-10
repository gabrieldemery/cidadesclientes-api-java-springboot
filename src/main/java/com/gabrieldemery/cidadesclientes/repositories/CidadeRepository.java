package com.gabrieldemery.cidadesclientes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrieldemery.cidadesclientes.models.Cidade;
import com.gabrieldemery.cidadesclientes.models.enums.Estados;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	List<Cidade> findByNomeOrEstado(String nome, Estados estado);
	
	List<Cidade> findByNome(String nome);
	
	List<Cidade> findByEstado(Estados estado);
	
}
