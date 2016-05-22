package org.cdoremus.jpa_sandbox.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.cdoremus.jpa_sandbox.domain.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ItemRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	public Long create(Item item) {
				
		entityManager.persist(item);
		
		return item.getItemId();
	}

	public Item find(Long id) {
		
		return entityManager.find(Item.class, id);
		
	}

}
