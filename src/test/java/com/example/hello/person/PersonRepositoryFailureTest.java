package com.example.hello.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryFailureTest {
	
	@Autowired
	private PersonRepository repository;
	
	@Test
	public void data_not_found() {
		// Find by lastname
		Optional<Person> someone = repository.findByLastName("Pui");
		
		// Check result
		assertNotNull(someone);
		assertFalse(someone.isPresent());
		assertEquals(Optional.empty(), someone);
	}

}
