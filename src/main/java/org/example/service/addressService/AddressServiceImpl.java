package org.example.service.addressService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.Address;
import org.example.repository.addressRepository.AddressRepository;
import org.hibernate.SessionFactory;

public class AddressServiceImpl extends BaseServiceImpl<Address, Long, AddressRepository> implements AddressService {
    public AddressServiceImpl(AddressRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
