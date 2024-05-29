package org.example.service.creditService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.Credit;
import org.example.repository.creditRepository.CreditRepository;
import org.hibernate.SessionFactory;

public class CreditServiceImpl extends BaseServiceImpl<Credit, Long, CreditRepository> implements CreditService {
    public CreditServiceImpl(CreditRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
