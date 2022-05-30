package com.luisribeiro.crudcliente.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luisribeiro.crudcliente.dto.ClientDTO;
import com.luisribeiro.crudcliente.entities.Client;
import com.luisribeiro.crudcliente.repositories.ClientRepository;
import com.luisribeiro.crudcliente.services.exceptions.DatabaseException;
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

	public void excluir(Long id) {
		try {
			clientRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncotradaExcetion("Id não encontrado." + id);
			
		}
		catch(DataIntegrityViolationException e) {
			throw new  DatabaseException("Violação de Integridade.");
		}
		
		
	}

	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list= clientRepository.findAll(pageRequest);
		return list.map(x ->new ClientDTO(x));
		
	}

}
