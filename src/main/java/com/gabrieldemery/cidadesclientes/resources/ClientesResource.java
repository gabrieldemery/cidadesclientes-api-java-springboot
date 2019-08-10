package com.gabrieldemery.cidadesclientes.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrieldemery.cidadesclientes.models.Cliente;
import com.gabrieldemery.cidadesclientes.repositories.ClienteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "")
@Api(value = "End Points de Clientes")
@CrossOrigin(origins = "*")
public class ClientesResource {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	@ApiOperation(value = "Listar Clientes")
	public List<Cliente> listarClientes(@RequestParam("id") Optional<Long> id, @RequestParam("nome") Optional<String> nome) {
		List<Cliente> lista = new ArrayList<Cliente>();
		
		if ( id.isPresent() && nome.isPresent() ) {
			lista = this.clienteRepository.findByIdOrNome(id.get(), nome.get());
		} else if ( id.isPresent() && !nome.isPresent() ) {
			Optional<Cliente> cliente = this.clienteRepository.findById(id.get());
			if (cliente.isPresent()) {
				lista.add(cliente.get());
			}
		} else if ( !id.isPresent() && nome.isPresent() ) {
			lista = this.clienteRepository.findByNome(nome.get());
		} else {
			lista = this.clienteRepository.findAll();
		}
		
		return lista;
	}
	
	@RequestMapping(value = "/cliente", method = RequestMethod.POST, consumes = "application/json")
	@ApiOperation(value = "Salvar Cliente")
	public Cliente salvarCliente(@RequestBody Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Remover Cliente")
	public void removerCliente(@PathVariable(value = "id") Long id) {
		this.clienteRepository.deleteById(id);
	}
	
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT, consumes = "text/plain")
	@ApiOperation(value = "Alterar Nome do Cliente")
	public void alterarNomeDoCliente(@PathVariable(value = "id") Long id, @RequestBody String nome) {
		this.clienteRepository.updateNome(id, nome);
	}
	
	

}
