package org.example.service.requestStatusService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.RequestStatus;
import org.example.repository.requestStatusRepository.RequestStatusRepository;
import org.hibernate.SessionFactory;

public class RequestStatusServiceImpl extends BaseServiceImpl<RequestStatus, Long, RequestStatusRepository>
        implements RequestStatusService {
    public RequestStatusServiceImpl(RequestStatusRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
