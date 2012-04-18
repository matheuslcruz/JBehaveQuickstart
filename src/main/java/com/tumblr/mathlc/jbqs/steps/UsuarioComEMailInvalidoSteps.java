package com.tumblr.mathlc.jbqs.steps;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.google.inject.Inject;
import com.tumblr.mathlc.jbqs.EMailInvalidoException;
import com.tumblr.mathlc.jbqs.EMailObrigatorioException;
import com.tumblr.mathlc.jbqs.Usuario;
import com.tumblr.mathlc.jbqs.UsuarioService;

public class UsuarioComEMailInvalidoSteps {
	private Usuario usuario;
	private Exception exception;
	
	@Inject
	private UsuarioService service;
	
	@Given("um usuário C com e-mail $e")
	public void setUsuario(String e) {
		usuario = service.getUsuario();
		usuario.setEmail(e);
	}
	
	@When("C for salvo")
	public void saveUsuario() throws EMailObrigatorioException {
		try {
			service.save(usuario);
		} catch (EMailInvalidoException e) {
			exception = e;
		}
	}
	
	@Then("a validade do e-mail deve impedir a operação.")
	public void throwEMailInvalidoException() {
		assertThat(exception, notNullValue());
		assertThat(exception instanceof EMailInvalidoException, equalTo(true));
	}
}
