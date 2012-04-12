package com.tumblr.mathlc.jbqs.steps;

import static org.mockito.Mockito.verify;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.google.inject.Inject;
import com.tumblr.mathlc.jbqs.EMailInvalidoException;
import com.tumblr.mathlc.jbqs.EMailObrigatorioException;
import com.tumblr.mathlc.jbqs.Usuario;
import com.tumblr.mathlc.jbqs.UsuarioRepository;
import com.tumblr.mathlc.jbqs.UsuarioService;

public class UsuarioValidoSteps {
	@Inject
	private UsuarioRepository repository;
	
	@Inject
	private UsuarioService service;
	
	private Usuario usuario;

	@Given("um usuário A com e-mail $e")
	public void setUsuario(String e) {
		usuario = service.getUsuario();
		usuario.setEmail(e);
	}
	
	@When("A for salvo")
	public void saveUsuario() throws EMailObrigatorioException, EMailInvalidoException {
		service.save(usuario);
	}
	
	@Then("A deve ser persistido pelo repositório.")
	public void persistA() {
		verify(repository).save(usuario);
	}
}
