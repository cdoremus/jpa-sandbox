package org.cdoremus.jpa_sandbox.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.cdoremus.jpa_sandbox.domain.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	public Long create(Customer customer) {
		
		entityManager.persist(customer);
		
		return customer.getUserId();
	}


	public Customer find(Long id) {
		
		Customer customer = entityManager.find(Customer.class, id);
		
		return customer;
	}

	public List<Customer> findAll() {
		Query query =  entityManager.createQuery("select c from Customer c", Customer.class);
		return (List<Customer>)query.getResultList();
	}
	
}
