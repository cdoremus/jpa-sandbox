package org.cdoremus.jpa_sandbox.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.cdoremus.jpa_sandbox.TestConfig;
import org.cdoremus.jpa_sandbox.domain.Customer;
import org.cdoremus.jpa_sandbox.domain.Item;
import org.cdoremus.jpa_sandbox.domain.Order;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OrderRepositoryTest {

	@Inject
	OrderRepository repository;
//	@Inject
//	ItemRepository itemRepository;
	@Inject
	CustomerRepository customerRepository;
	
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
	public void testCreate_OrderWithCustomerAndItems() {

		Customer customer = new Customer("first", "last", "last@email.com", "100 main st", "New York", "NY", "12054");
		customerRepository.create(customer);
		
		Order order = new Order("Order 1");
		order.setCustomer(customer);
		Item item1 = new Item("Item 1");
		order.addItem(item1);
		Item item2 = new Item("Item 2");
		order.addItem(item2);
		
		long id = repository.create(order);
		
		assertTrue(id > 0);
		assertEquals(2, order.getItems().size());
		assertEquals("first", order.getCustomer().getFirstName());
	}

	@Test
	public void testCreate_OrderWithCustomerAndNoItems() {

		Customer customer = new Customer("first", "last", "last@email.com", "100 main st", "New York", "NY", "12054");
		customerRepository.create(customer);
		
		Order order = new Order("Order 1");
		order.setCustomer(customer);
		
		long id = repository.create(order);
		
		assertTrue(id > 0);
		assertEquals(0, order.getItems().size());
		assertEquals("first", order.getCustomer().getFirstName());
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testCreate_CustomerNotPersisted_ThrowsException() {

		Customer customer = new Customer("first", "last", "last@email.com", "100 main st", "New York", "NY", "12054");
//		customerRepository.create(customer); <-removal of call causes exception
		
		Order order = new Order("Order 1");
		order.setCustomer(customer);
		
		repository.create(order);
		
	}
	
	@Test
	public void testFind() throws Exception {

		Customer customer = new Customer("first", "last", "last@email.com", "100 main st", "New York", "NY", "12054");
		customerRepository.create(customer);
		
		Order order = new Order("Order 1");
		order.setCustomer(customer);
		
		Item item1 = new Item("Item 1");
		order.addItem(item1);
		Item item2 = new Item("Item 2");
		order.addItem(item2);
		
		Long id = repository.create(order);
		
		Order newOrder = repository.find(id);

		assertEquals("Order 1", newOrder.getName());
	
	
	}


	@Test
	public void testFindByName() throws Exception {

		Customer customer = new Customer("first1", "last1", "last1@email.com", "101 main st", "Gray", "ME", "04260");
		Long customerId = customerRepository.create(customer);
		
		Order order = new Order("Order 1");
		order.setCustomer(customer);
		
//		order.addItem(item);
		
		long id = repository.create(order);
		
		Order newOrder = repository.findByName("Order 1");

		assertEquals("Order 1", newOrder.getName());
	
		System.out.println("New order: " + newOrder);
		System.out.println("Customer Id: " + customerId);
		assertEquals(customerId, newOrder.getCustomer().getUserId());
		
	}
}
