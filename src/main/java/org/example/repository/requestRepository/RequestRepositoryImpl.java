package org.example.repository.requestRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.Request;
import org.hibernate.SessionFactory;

public class RequestRepositoryImpl extends BaseRepositoryImpl<Request, Long> implements RequestRepository {
    public RequestRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Request> getEntityClass() {
        return Request.class;
    }
}
