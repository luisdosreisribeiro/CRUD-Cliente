package com.luisribeiro.crudcliente.controller.exceptions;

import java.time.Instant;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luisribeiro.crudcliente.services.exceptions.EntidadeNaoEncotradaExcetion;

@ControllerAdvice
public class ControllerExceptionsHandler {
	
	@ExceptionHandler(EntidadeNaoEncotradaExcetion.class)
	public ResponseEntity<StandardError>entityNotFound(EntidadeNaoEncotradaExcetion e,HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Recurso não encontrado");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}

}