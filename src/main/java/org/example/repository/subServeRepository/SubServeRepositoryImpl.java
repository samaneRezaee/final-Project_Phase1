package org.example.repository.subServeRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.SubServe;
import org.hibernate.SessionFactory;

public class SubServeRepositoryImpl extends BaseRepositoryImpl<SubServe, Long> implements SubServeRepository {
    public SubServeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<SubServe> getEntityClass() {
        return SubServe.class;
    }
}
