package org.example.repository.requestStatusRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.RequestStatus;
import org.hibernate.SessionFactory;

public class RequestStatusRepositoryImpl extends BaseRepositoryImpl<RequestStatus, Long> implements RequestStatusRepository {

    public RequestStatusRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<RequestStatus> getEntityClass() {
        return RequestStatus.class;
    }
}
