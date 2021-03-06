package com.example.hello.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {
	
	@Autowired
	private PersonRepository repository;
	
	@Test
	public void success() {
		// Prepare data
		Person somkiat = new Person("Somkiat", "Pui");
		repository.save(somkiat);
		
		// Find by lastname
		Optional<Person> someone = repository.findByLastName("Pui");
		
		// Check result
		assertTrue(someone.isPresent());
		assertNotNull(someone.get());
		assertEquals("Somkiat", someone.get().getFirstName());
	}

}
