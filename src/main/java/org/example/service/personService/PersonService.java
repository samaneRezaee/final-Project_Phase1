package org.example.service.personService;

import org.example.base.service.BaseService;
import org.example.model.Person;

public interface PersonService extends BaseService<Person, Long> {
    Person findByUsername(String username);
    boolean isExistEmail(String email);

}
