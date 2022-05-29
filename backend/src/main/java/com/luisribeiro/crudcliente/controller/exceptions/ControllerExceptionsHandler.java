package com.luisribeiro.crudcliente.controller.exceptions;

import java.time.Instant;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luisribeiro.crudcliente.services.exceptions.DatabaseException;
import com.luisribeiro.crudcliente.services.exceptions.EntidadeNaoEncotradaExcetion;

@ControllerAdvice
public class ControllerExceptionsHandler {
	
	@ExceptionHandler(EntidadeNaoEncotradaExcetion.class)
	public ResponseEntity<StandardError>entityNotFound(EntidadeNaoEncotradaExcetion e,HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Recurso não encontrado");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError>database(DatabaseException e,HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database Exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
	}


}
