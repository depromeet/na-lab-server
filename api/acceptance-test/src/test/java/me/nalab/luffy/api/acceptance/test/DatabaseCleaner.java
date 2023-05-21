package me.nalab.luffy.api.acceptance.test;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseCleaner {

	@PersistenceContext
	protected EntityManager entityManager;

	@Transactional
	@Modifying
	public void dbCleanUp(Set<String> tableNameSet) {
		entityManager.createNativeQuery(H2Dialect.REFERER_FALSE).executeUpdate();
		tableNameSet.forEach(n -> entityManager.createNativeQuery(H2Dialect.TRUNCATE + n));
		entityManager.createNativeQuery(H2Dialect.REFERER_TRUE).executeUpdate();
	}

	private static final class H2Dialect {
		private static final String REFERER_TRUE = "SET REFERENTIAL_INTEGRITY TRUE";
		private static final String REFERER_FALSE = "SET REFERENTIAL_INTEGRITY FALSE";
		private static final String TRUNCATE = "TRUNCATE TABLE ";
	}

}
