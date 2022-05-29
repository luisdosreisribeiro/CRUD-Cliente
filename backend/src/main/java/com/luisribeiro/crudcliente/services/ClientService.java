package com.luisribeiro.crudcliente.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

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
	public List<ClientDTO> findAll() {

		List<Client> list = clientRepository.findAll();

		List<ClientDTO> listDTO = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());

		return listDTO;

	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = clientRepository.findById(id);
		Client entity = obj.orElseThrow(() -> new EntidadeNaoEncotradaExcetion("Entidade não encontradaa!"));
		return new ClientDTO(entity);

	}

	@Transactional
	public ClientDTO save(ClientDTO clientDto) {
		Client entity = new Client();
		entity.setName(clientDto.getName());
		entity.setBirthDate(clientDto.getBirthDate());
		entity.setChildren(clientDto.getChildren());
		entity.setIncome(clientDto.getIncome());
		entity.setCpf(clientDto.getCpf());

		entity = clientRepository.save(entity);
		return new ClientDTO(entity);

	}

	@Transactional
	public ClientDTO atualizar(ClientDTO clientDto, Long id) {
		try {
			Client entity = clientRepository.getOne(id);
			entity.setBirthDate(clientDto.getBirthDate());
			entity.setChildren(clientDto.getChildren());
			entity.setCpf(clientDto.getCpf());
			entity.setIncome(clientDto.getIncome());
			entity.setName(clientDto.getName());

			entity = clientRepository.save(entity);
			return new ClientDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncotradaExcetion("Id não encontrado: " + id);
		}

	}

}
