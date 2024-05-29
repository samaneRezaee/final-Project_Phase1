package org.example.service.personService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.Person;
import org.example.repository.personRepository.PersonRepository;
import org.hibernate.SessionFactory;

public class PersonServiceImpl extends BaseServiceImpl<Person, Long, PersonRepository> implements PersonService {
    public PersonServiceImpl(PersonRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
