package com.gabrieldemery.cidadesclientes.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gabrieldemery.cidadesclientes.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	List<Cliente> findByIdOrNome(Long id, String nome);
	
	Optional<Cliente> findById(Long id);
	
	List<Cliente> findByNome(String nome);
	
	@Modifying
    @Query("UPDATE Cliente cliente SET cliente.nome = :nome WHERE cliente.id = :id")
	@Transactional
    Integer updateNome(@Param("id") Long id, @Param("nome") String nome);
	
}