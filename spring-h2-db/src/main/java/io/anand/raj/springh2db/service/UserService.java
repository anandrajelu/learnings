package io.anand.raj.springh2db.service;

import io.anand.raj.springh2db.entity.Person;
import io.anand.raj.springh2db.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ApplicationContext context;
    public Person add(String userName,
                      String firstName,
                      String lastName,
                      String type) {
        Person person = new Person();
        person.setUserName(userName);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setType(type);
        person.setJoinedOn(new Date());
        person.setModifiedOn(new Date());
        return personRepository.save(person);
    }
}
