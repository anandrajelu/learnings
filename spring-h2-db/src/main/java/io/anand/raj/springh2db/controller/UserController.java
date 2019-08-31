package io.anand.raj.springh2db.controller;

import java.util.Date;
import java.util.List;

import io.anand.raj.springh2db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.anand.raj.springh2db.entity.EftTransaction;
import io.anand.raj.springh2db.entity.Person;
import io.anand.raj.springh2db.repository.EftTransactionRepository;
import io.anand.raj.springh2db.repository.PersonRepository;

@RestController
@RequestMapping("rest")
public class UserController {

	@Autowired
	PersonRepository personRepository;
	@Autowired
	UserService userService;
	@Autowired
	EftTransactionRepository eftTransactionRepository;
	@Autowired
	ApplicationContext context;
	
	@PostMapping("person")
	public Person add(@RequestHeader String userName,
			@RequestHeader String firstName, 
			@RequestHeader String lastName,
            @RequestHeader String type) {
		return userService.add(userName, firstName, lastName, type);
	}
	
	@GetMapping("person/{id}")
	public Person get(@PathVariable long id) {
		Person person = personRepository.findById(id).get();
		return person;
	}

	@GetMapping("person")
	public List<Person> getByMatching(@RequestParam String pattern, @RequestParam String type) {
		return personRepository.findUsersByMatching("%"+pattern+"%", type);
	}
}
