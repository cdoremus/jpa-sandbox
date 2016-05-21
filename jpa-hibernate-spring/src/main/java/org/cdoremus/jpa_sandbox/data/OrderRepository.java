package org.cdoremus.jpa_sandbox.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.cdoremus.jpa_sandbox.domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	public Long create(Order order) {
				
		entityManager.persist(order);
		
		return order.getOrderId();
	}


	public Order find(Long id) {
		return entityManager.find(Order.class,id);
	}
	
	public Order findByName(String orderName) {
		Query query = entityManager.createQuery("select o from Order o where o.name = ?1", Order.class);
		query.setParameter(1, orderName);
		return (Order)query.getSingleResult();
	}
	
	public List<Order> findAll() {
		Query query =  entityManager.createQuery("select o from Order o", Order.class);
		return (List<Order>)query.getResultList();
	}
	
}
