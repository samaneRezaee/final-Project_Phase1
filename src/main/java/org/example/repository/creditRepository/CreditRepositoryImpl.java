package org.example.repository.creditRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.Credit;
import org.hibernate.SessionFactory;

public class CreditRepositoryImpl extends BaseRepositoryImpl<Credit, Long> implements CreditRepository {
    public CreditRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Credit> getEntityClass() {
        return Credit.class;
    }
}
