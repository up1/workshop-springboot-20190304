package com.example.hello.person;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.After;
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

    @After
    public void tearDown() throws Exception {
    	repository.deleteAll();
    }
    
    @Test
    public void should_save_fetch_a_person() {
    	
    	Person somkiat = new Person("Somkiat", "Pui");
    	repository.save(somkiat);

        Optional<Person> maybeSomkiat 
                           = repository.findByLastName("Pui");

        assertEquals(maybeSomkiat, Optional.of(somkiat));
        
    }

}

