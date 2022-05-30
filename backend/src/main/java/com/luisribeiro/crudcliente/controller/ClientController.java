package com.luisribeiro.crudcliente.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luisribeiro.crudcliente.dto.ClientDTO;
import com.luisribeiro.crudcliente.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;

	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> listarPaginado(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		
		Page<ClientDTO> list = clientService.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(list);

	}

	@GetMapping(value = ("/{id}"))
	public ResponseEntity<ClientDTO> buscar(@PathVariable Long id) {
		ClientDTO clientDto = clientService.findById(id);
		return ResponseEntity.ok(clientDto);
	}

	@PostMapping
	public ResponseEntity<ClientDTO> inserir(@RequestBody ClientDTO clientDto) {
		clientDto = clientService.save(clientDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clientDto.getId()).toUri();
		return ResponseEntity.created(uri).body(clientDto);

	}
	
	@PutMapping(value = "/{id}" )
	public ResponseEntity<ClientDTO> atualizar(@RequestBody ClientDTO clientDto,@PathVariable Long id) {
		clientDto = clientService.atualizar(clientDto, id);
		
		return ResponseEntity.ok().body(clientDto);
				

	}
	
	@DeleteMapping(value = ("/{id}"))
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		clientService.excluir(id);
		return ResponseEntity.noContent().build();
		
		
	}
	

}
