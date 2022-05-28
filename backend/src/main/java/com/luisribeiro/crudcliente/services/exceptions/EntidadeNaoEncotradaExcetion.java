package com.luisribeiro.crudcliente.services.exceptions;

public class EntidadeNaoEncotradaExcetion extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncotradaExcetion(String msg) {
		super(msg);
	}

}
