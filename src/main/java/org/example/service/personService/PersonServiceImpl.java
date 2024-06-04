package org.example.service.personService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.Person;
import org.example.repository.personRepository.PersonRepository;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

public class PersonServiceImpl extends BaseServiceImpl<Person, Long, PersonRepository> implements PersonService {
    public PersonServiceImpl(PersonRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public Person findByUsername(String username) {
        try {
            return repository.findByUsername(username);
        } catch (HibernateException e) {
            System.out.println("PersonRepoImpl" + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean isExistEmail(String email) {
        try {
            return repository.isExistEamil(email);
        }catch (HibernateException e){
            System.out.println("PersonRepoImpl" + e.getMessage());
        }
        return false;
    }
}
