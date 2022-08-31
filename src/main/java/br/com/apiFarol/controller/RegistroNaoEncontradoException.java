package br.com.apiFarol.controller;

public class RegistroNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegistroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
