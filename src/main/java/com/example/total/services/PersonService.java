package com.example.total.services;

import com.example.total.models.Person;
import com.example.total.models.Product;
import com.example.total.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
@Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Person findByLogin(Person person){
        Optional<Person> personDB = personRepository.findByLogin(person.getLogin());
        return  personDB.orElse(null);
    }
    @Transactional
    public void addPerson(Person person){
    person.setPassword(passwordEncoder.encode(person.getPassword()));
    person.setRole("ROLE_USER");
    personRepository.save(person);
    }
    public List<Person> getAllUsers(){
        return personRepository.findAll();
    }

    public Person getPersonById(int id){
        Optional<Person> personOptional = personRepository.findById(id);
        return  personOptional.orElse(null);
    }
    @Transactional
    public void changeRoleToAdmin(int id){
        Optional<Person> person = personRepository.findById(id);
        person.get().setRole("ROLE_ADMIN");
    }
    @Transactional
    public void changeRoleToUser(int id){
        Optional<Person> person = personRepository.findById(id);
        person.get().setRole("ROLE_USER");
    }

}
