package me.nalab.survey.jpa.adaptor.create;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import me.nalab.survey.application.port.out.persistence.LatestSurveyIdFindPort;

@Repository
public class LatestSurveyIdFindAdaptor implements LatestSurveyIdFindPort {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Long> findLatestSurveyIdByTargetId(Long targetId) {
		try {
			Long result = entityManager.createQuery(
					"select se.id from SurveyEntity se where se.targetId = :targetId order by se.createdAt desc",
					Long.class)
				.setParameter("targetId", targetId)
				.setMaxResults(1)
				.getSingleResult();
			return Optional.of(result);
		} catch(NoResultException noResultException) {
			return Optional.empty();
		}
	}

}
