package org.cdoremus.jpa_sandbox.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.cdoremus.jpa_sandbox.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	public Long create(User user) {
		
		entityManager.persist(user);
		
		return user.getUserId();
	}
}
