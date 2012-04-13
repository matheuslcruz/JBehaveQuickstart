package com.tumblr.mathlc.jbqs.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import com.google.inject.Inject;
import com.tumblr.mathlc.jbqs.Implemented;
import com.tumblr.mathlc.jbqs.Usuario;
import com.tumblr.mathlc.jbqs.UsuarioRepository;

public class ConsultaUsuariosSteps {
	private List<String> result;
	private EntityTransaction entityTransaction;

	@Inject
	private EntityManager entityManager;
	
	@Inject
	@Implemented
	private UsuarioRepository repository;
	
	@BeforeScenario
	public void setUp() {
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
	}

	@Given("os usuários: $us")
	public void setUsuarios(ExamplesTable us) {
		for (Map<String, String> i: us.getRows()) {
			Usuario u = new Usuario();
			u.setEmail(i.get("email"));
			repository.save(u);
		}
	}
	
	@When("consultar por $f")
	public void search(String f) {
		result = new ArrayList<String>();
		
		for (Usuario u: repository.search(f)) {
			result.add(u.getEmail());
		}
	}
	
	@Then("deve retornar os usuários $a e $b.")
	public void assertInclude(String a, String b) {
		assertThat(result.containsAll(Arrays.asList(a, b)), equalTo(true));
	}
	
	@AfterScenario
	public void tearDown() {
		entityTransaction.commit();
	}
}
