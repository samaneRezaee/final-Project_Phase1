package org.example.repository.personRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.conncetion.SessionFactorySingleton;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Person, Long> implements PersonRepository {
    public PersonRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Person> getEntityClass() {
        return Person.class;
    }


    @Override
    public Person findByUsername(String username) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hql = "FROM Person p WHERE p.username = :username";
        Query<Person> query = session.createQuery(hql, Person.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public boolean isExistEamil(String email) {
        boolean isExist=false;
        if(findByUsername(email)!=null)
            isExist=true;
        return isExist;
    }

}
