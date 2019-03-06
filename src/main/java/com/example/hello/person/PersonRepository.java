package com.example.hello.person;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository 
				 extends CrudRepository<Person, Long> {

    Optional<Person> findByLastName(String lastName);

}


