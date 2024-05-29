package org.example.service.serveService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.Serve;
import org.example.repository.serveRepository.ServeRepository;
import org.hibernate.SessionFactory;

public class ServeServiceImpl extends BaseServiceImpl<Serve, Long, ServeRepository> implements ServeService {
    public ServeServiceImpl(ServeRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
