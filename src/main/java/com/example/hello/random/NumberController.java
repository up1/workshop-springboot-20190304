package com.example.hello.random;

import java.text.MessageFormat;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.person.Person;
import com.example.hello.person.PersonRepository;

@RestController
public class NumberController {
	
	private MyRandom random;

	@Autowired
	public NumberController(MyRandom random) {
		this.random = random;
	}
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/name/{lastname}")
	public NameResponse searchNameByLastname
	                   (@PathVariable String lastname) {
		
		Optional<Person> person 
			= personRepository.findByLastName(lastname);
		
		if(person.isPresent()) {
			Person someone = person.get();
			return new NameResponse(String.format("%s %s", 
					 someone.getFirstName(), someone.getLastName()));
		}
		
		throw new PersonNotFoundException();
		
	}
	
	@PostConstruct
	public void initData() {
		personRepository.save(new Person("Somkiat", "pui"));
	}

	@GetMapping("/number")
	public NumberControllerResponse randomNumber() {
		int result = this.random.nextInt(10);  
		return new NumberControllerResponse(result + "");
	}
	
}
