package me.nalab.luffy.api.acceptance.test;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractDatabaseCleaner {

	protected EntityManager entityManager;

	@PersistenceContext
	final void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@BeforeEach
	@Transactional
	void dbCleanUp() {
		Set<String> tableNameSet = getTableNameCollection();
		entityManager.createNativeQuery(H2Dialect.REFERER_FALSE).executeUpdate();
		tableNameSet.forEach(n -> entityManager.createNamedQuery(H2Dialect.TRUNCATE + n));
		entityManager.createNativeQuery(H2Dialect.REFERER_TRUE).executeUpdate();
	}

	protected abstract Set<String> getTableNameCollection();

	private static final class H2Dialect {
		private static final String REFERER_TRUE = "SET REFERENTIAL_INTEGRITY TRUE";
		private static final String REFERER_FALSE = "SET REFERENTIAL_INTEGRITY FALSE";
		private static final String TRUNCATE = "TRUNCATE TABLE ";
	}

}
