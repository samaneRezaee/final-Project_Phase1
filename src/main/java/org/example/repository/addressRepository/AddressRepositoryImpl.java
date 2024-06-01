package org.example.repository.addressRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.Address;
import org.hibernate.SessionFactory;

public class AddressRepositoryImpl extends BaseRepositoryImpl<Address, Long> implements AddressRepository {
    public AddressRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Address> getEntityClass() {
        return Address.class;
    }
}
