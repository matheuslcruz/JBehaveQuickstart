package com.tumblr.mathlc.jbqs;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;

public class UsuarioJpaRepository implements UsuarioRepository {
	@Inject
	private EntityManager entityManager;
	
	public void save(Usuario u) {
		entityManager.persist(u);
		entityManager.flush();
	}
	
	@SuppressWarnings("unchecked")
	public 	List<Usuario> search(String f) {
		return (List<Usuario>) entityManager.createNamedQuery("search")
					.setParameter("filter", "%" + f + "%")
					.getResultList();
	}
}
