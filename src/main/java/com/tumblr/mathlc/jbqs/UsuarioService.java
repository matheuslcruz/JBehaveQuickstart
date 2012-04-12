package com.tumblr.mathlc.jbqs;

import com.google.inject.Inject;

public class UsuarioService {
	@Inject
	private UsuarioRepository repository;
	
	public Usuario getUsuario() {
		return new Usuario();
	}
	
	public void save(Usuario u) throws EMailObrigatorioException, EMailInvalidoException {
		if (u.getEmail() == null) {
			throw new EMailObrigatorioException();
		} else if (u.getEmail().indexOf("@") < 1) {
			throw new EMailInvalidoException();
		}
		
		repository.save(u);
	}
}
