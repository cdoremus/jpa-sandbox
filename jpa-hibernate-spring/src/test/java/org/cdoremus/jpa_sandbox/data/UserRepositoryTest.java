package org.cdoremus.jpa_sandbox.data;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import org.cdoremus.jpa_sandbox.TestConfig;
import org.cdoremus.jpa_sandbox.domain.User;
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
public class UserRepositoryTest {

	@Inject
	UserRepository repository;
	
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
		User user = new User("first", "last", "first@email.com");

		long id = repository.create(user);
		
		assertTrue(id > 0);
		
	}

	@Test
	public void testCreate_BadEmail() {
		User user = new User("first", "last", "firstemail.com");

		try {
			repository.create(user);
			fail("Should not get here as ConstraintViolationException should be thrown");
		} catch (ConstraintViolationException e) {
			String message = e.getConstraintViolations().iterator().next().getMessage();
			assertEquals("not a well-formed email address", message);
		} catch (Throwable t) {
			fail("Should only throw a ConstraintViolationException");
		}
	}

	@Test(expected=ConstraintViolationException.class)
	public void testCreate_NullLastName() {
		User user = new User("first", null, "first@email.com");

		long id = repository.create(user);
		
		assertTrue(id > 0);
		
	}

}
