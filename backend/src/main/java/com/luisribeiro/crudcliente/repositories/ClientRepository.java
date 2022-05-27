package com.luisribeiro.crudcliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luisribeiro.crudcliente.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
