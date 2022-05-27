package com.luisribeiro.crudcliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisribeiro.crudcliente.entities.Client;
import com.luisribeiro.crudcliente.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<Client>> listar(){
		List<Client> list = clientService.findAll();
		return ResponseEntity.ok().body(list);
		
	}

}
