package com.luisribeiro.crudcliente.entities.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisribeiro.crudcliente.entities.Client;
import com.luisribeiro.crudcliente.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public List<Client>findAll(){
		return clientRepository.findAll();
	}

}