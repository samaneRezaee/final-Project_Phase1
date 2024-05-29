package org.example.repository.serveRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.Serve;
import org.hibernate.SessionFactory;

public class ServeRepositoryImpl extends BaseRepositoryImpl<Serve, Long> implements ServeRepository {
    public ServeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Serve> getEntityClass() {
        return Serve.class;
    }
}
