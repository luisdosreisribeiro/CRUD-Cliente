package com.luisribeiro.crudcliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisribeiro.crudcliente.dto.ClientDTO;
import com.luisribeiro.crudcliente.entities.Client;
import com.luisribeiro.crudcliente.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> listar(){
		List<ClientDTO> list = clientService.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = ("/{id}"))
	public ResponseEntity<ClientDTO> buscar(@PathVariable Long id){
		ClientDTO clientDto = clientService.findById(id);
		return ResponseEntity.ok(clientDto);
	}
	@PostMapping
	public ResponseEntity<Client> inserir(@RequestBody Client client){
		Client cli = clientService.save(client);
		return ResponseEntity.ok(client);
		
		
		
	}

}
