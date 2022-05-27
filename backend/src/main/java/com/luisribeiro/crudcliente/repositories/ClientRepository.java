package com.luisribeiro.crudcliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.luisribeiro.crudcliente.entities.Client;

@Component
public interface ClientRepository extends JpaRepository<Client, Long> {

}
