package com.luisribeiro.crudcliente.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luisribeiro.crudcliente.dto.ClientDTO;
import com.luisribeiro.crudcliente.entities.Client;
import com.luisribeiro.crudcliente.repositories.ClientRepository;
import com.luisribeiro.crudcliente.services.exceptions.EntidadeNaoEncotradaExcetion;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll(){
		
		List<Client> list = clientRepository.findAll();
		
		List<ClientDTO> listDTO = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
		
		return listDTO;
		
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {		
		Optional<Client> obj =   clientRepository.findById(id);
		Client entity = obj.orElseThrow(() -> new EntidadeNaoEncotradaExcetion("Entidade não encontradaa!"));
		return new ClientDTO(entity);
		
		
		
	}

	public Client save(Client client) {
		clientRepository.save(client);
		return client;
	}

}
