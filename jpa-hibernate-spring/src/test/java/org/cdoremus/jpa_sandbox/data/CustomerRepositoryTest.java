package org.cdoremus.jpa_sandbox.data;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import org.cdoremus.jpa_sandbox.TestConfig;
import org.cdoremus.jpa_sandbox.domain.Customer;
import org.cdoremus.jpa_sandbox.domain.Order;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CustomerRepositoryTest {

	@Inject
	CustomerRepository repository;
	@Inject
	OrderRepository orderRepository;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		
		Customer customer = new Customer("first", "last", "last@email.com", "100 main st", "New York", "NY", "12054");
		Order order = new Order("order 1");
		customer.addOrder(order);
		
		long id = repository.create(customer);
		
		System.out.println("Customer persisted: " + customer);
		
		assertTrue(id > 0);

	}

	@Test
	public void testCreate_NoOrder() {
		
		Customer customer = new Customer("first", "last", "last@email.com", "100 main st", "New York", "NY", "12054");
		
		long id = repository.create(customer);
		
		System.out.println("Customer persisted: " + customer);
		
		assertTrue(id > 0);

	}

	@Test
	public void testFind() {
		
		Order order1 = new Order("order 1");
		Order order2 = new Order("order 2");
		Customer customer = new Customer("first2", "last2", "last2@email.com", "200 main st", "New York", "NY", "12054");
		customer.addOrder(order1);
		customer.addOrder(order2);
		
		long id = repository.create(customer);
		
		Customer found = repository.find(id);
		
		System.out.println("Customer found: " + found);
//		System.out.println("Order Id: " + order1.getOrderId());

		assertEquals(2, found.getOrders().size());
		
	}
	
	@Test
	public void testFindCustomerOrderByName() throws Exception {
		Order order1 = new Order("order 1");
//		orderRepository.create(order1);
		Order order2 = new Order("order 2");
//		orderRepository.create(order2);
		Customer customer = new Customer("first2", "last2", "last2@email.com", "200 main st", "New York", "NY", "12054");
		customer.addOrder(order1);
		customer.addOrder(order2);
		
		Long id = repository.create(customer);
		
		Customer newCustomer = repository.find(id);
		
		System.out.println("New customer: " + newCustomer);
		
		List<Order> orders = orderRepository.findAll();
		System.out.println("Customer orders in CustomerOrder table:");
		for (Order order : orders) {
			System.out.println("Order: " + order.getName());
		}
		
	}

	@Test
	public void testCustomerWithInvalidZipCode() {
		
		Customer customer = new Customer("first", "last", "last@email.com", "100 main st", "New York", "NY", "1205");
		
		try {
			repository.create(customer);
			fail("Should throw exception");
		} catch (ConstraintViolationException e) {
			String message = e.getConstraintViolations().iterator().next().getMessage();
			assertEquals("A valid zip code must be entered.", message);
		}
		

	}
}
