package org.example.service.customerService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.Customer;
import org.example.repository.customerRepository.CustomerRepository;
import org.hibernate.SessionFactory;

public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long, CustomerRepository> implements CustomerService {
    public CustomerServiceImpl(CustomerRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
