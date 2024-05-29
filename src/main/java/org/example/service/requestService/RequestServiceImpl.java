package org.example.service.requestService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.Request;
import org.example.repository.requestRepository.RequestRepository;
import org.hibernate.SessionFactory;

public class RequestServiceImpl extends BaseServiceImpl<Request, Long, RequestRepository> implements RequestService {
    public RequestServiceImpl(RequestRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
