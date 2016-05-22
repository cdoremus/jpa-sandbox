package org.cdoremus.jpa_sandbox.data;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.cdoremus.jpa_sandbox.TestConfig;
import org.cdoremus.jpa_sandbox.domain.Item;
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
public class ItemRepositoryTest {

	@Inject
	private ItemRepository repository;
	
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
		Item item = new Item("Item 1");
		
		Long id = repository.create(item);
		
		assertTrue(id > 0);
	}

	@Test
	public void testFind() {
		Item item = new Item("Item 1");
		
		Long id = repository.create(item);
		
		Item found = repository.find(id);
		
		assertEquals("Item 1", found.getName());
	}

}
