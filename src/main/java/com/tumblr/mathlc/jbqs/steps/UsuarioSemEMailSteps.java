package com.tumblr.mathlc.jbqs.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.google.inject.Inject;
import com.tumblr.mathlc.jbqs.EMailInvalidoException;
import com.tumblr.mathlc.jbqs.EMailObrigatorioException;
import com.tumblr.mathlc.jbqs.Usuario;
import com.tumblr.mathlc.jbqs.UsuarioService;

public class UsuarioSemEMailSteps {
	private Usuario usuario;
	private Exception exception;
	
	@Inject
	private UsuarioService service;

	@Given("um usuário B sem e-mail")
	public void setUsuario() {
		usuario = new Usuario();
	}
	
	@When("B for salvo")
	public void saveUsuario() throws EMailInvalidoException {
		try {
			service.save(usuario);
		} catch (EMailObrigatorioException e) {
			exception = e;
		}
	}
	
	@Then("a obrigatoriedade do e-mail deve impedir a operação.")
	public void throwEMailObrigatorioException() {
		assertThat(exception, notNullValue());
		assertThat(exception instanceof EMailObrigatorioException, equalTo(true));
	}
}
