package com.luisribeiro.crudcliente.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisribeiro.crudcliente.dto.ClientDTO;
import com.luisribeiro.crudcliente.entities.Client;
import com.luisribeiro.crudcliente.repositories.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	public List<ClientDTO> findAll(){
		
		List<Client> list = clientRepository.findAll();
		
		List<ClientDTO> listDTO = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
		
		return listDTO;
		
	}

}
