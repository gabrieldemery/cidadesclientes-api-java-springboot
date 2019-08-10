package com.gabrieldemery.cidadesclientes.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrieldemery.cidadesclientes.models.Cidade;
import com.gabrieldemery.cidadesclientes.models.enums.Estados;
import com.gabrieldemery.cidadesclientes.repositories.CidadeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="")
@Api(value = "End Points de Cidades")
@CrossOrigin(origins = "*")
public class CidadesResource {
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@RequestMapping(value = "/cidades", method = RequestMethod.GET)
	@ApiOperation(value = "Listar Cidades")
	public List<Cidade> listarCidadesPorNome(@RequestParam("nome") Optional<String> nome, @RequestParam("estado") Optional<Estados> estado) {
		List<Cidade> lista = new ArrayList<Cidade>();
		
		if ( nome.isPresent() && estado.isPresent() ) {
			lista = this.cidadeRepository.findByNomeOrEstado(nome.get(), estado.get());
		} else if ( nome.isPresent() && !estado.isPresent() ) {
			lista = this.cidadeRepository.findByNome(nome.get());
		} else if ( !nome.isPresent() && estado.isPresent() ) {
			lista = this.cidadeRepository.findByEstado(estado.get());
		} else {
			lista = this.cidadeRepository.findAll();
		}
		
		return lista;
	}
	
	@RequestMapping(value = "/cidade", method = RequestMethod.POST, consumes = "application/json")
	@ApiOperation(value = "Salvar Cliente")
	public Cidade salvarCidade(@RequestBody Cidade cidade) {
		return this.cidadeRepository.save(cidade);
	}
	
}
