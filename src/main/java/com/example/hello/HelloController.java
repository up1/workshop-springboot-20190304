package com.example.hello;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.person.Person;
import com.example.hello.person.PersonRepository;

@RestController
public class HelloController {
	
	private final PersonRepository personRepository;
	
	@Autowired
    public HelloController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
	
	@GetMapping("/hello/{lastName}")
    public HelloResponse hello(@PathVariable final String lastName) {
		
        Optional<Person> foundPerson 
                   = personRepository.findByLastName(lastName);

        return foundPerson
                .map(person -> 
                     new HelloResponse(person.getFirstName(), 
                    		           person.getLastName()))
                .orElseThrow(() -> new RuntimeException());
    }
	
}
