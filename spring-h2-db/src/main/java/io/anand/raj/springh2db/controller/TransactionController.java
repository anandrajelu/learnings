package io.anand.raj.springh2db.controller;

import io.anand.raj.springh2db.entity.EftTransaction;
import io.anand.raj.springh2db.entity.Person;
import io.anand.raj.springh2db.repository.EftTransactionRepository;
import io.anand.raj.springh2db.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("rest")
public class TransactionController {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    EftTransactionRepository eftTransactionRepository;
    @Autowired
    ApplicationContext context;

    @PostMapping("txn")
    public EftTransaction initiateTxn(@RequestParam long amount, @RequestParam long personId,
                                      @RequestParam long toPersonId) {
        Person person = personRepository.findById(personId).get();
        Person toPerson = personRepository.findById(toPersonId).get();
        EftTransaction eftTransaction = new EftTransaction();
        eftTransaction.setPerson(person);
        eftTransaction.setToPerson(toPerson);
        eftTransaction.setAmount(amount);
        eftTransaction.setModifiedOn(new Date());
        eftTransaction.setInitiatedOn(new Date());
        eftTransaction.setType("ONE_TIME");
        eftTransactionRepository.save(eftTransaction);
        return eftTransaction;
    }
}
