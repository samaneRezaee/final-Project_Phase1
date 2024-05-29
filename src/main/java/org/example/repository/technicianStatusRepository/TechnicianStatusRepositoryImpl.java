package org.example.repository.technicianStatusRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.TechnicianStatus;
import org.hibernate.SessionFactory;

public class TechnicianStatusRepositoryImpl extends BaseRepositoryImpl<TechnicianStatus, Long> implements TechnicianStatusRepository {
    public TechnicianStatusRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<TechnicianStatus> getEntityClass() {
        return TechnicianStatus.class;
    }
}
