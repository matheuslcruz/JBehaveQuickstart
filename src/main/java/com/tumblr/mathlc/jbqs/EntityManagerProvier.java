package com.tumblr.mathlc.jbqs;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.google.inject.Provider;

public class EntityManagerProvier implements Provider<EntityManager> {
	private static final String PERSISTENCE_UNIT_NAME = "jbehave-quickstart";

	public EntityManager get() {
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
				.createEntityManager();
	}
}
