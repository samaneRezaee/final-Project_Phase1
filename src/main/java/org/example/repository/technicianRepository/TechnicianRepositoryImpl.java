package org.example.repository.technicianRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.Technician;
import org.hibernate.SessionFactory;

public class TechnicianRepositoryImpl extends BaseRepositoryImpl<Technician, Long>implements TechnicianRepository {
    public TechnicianRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Technician> getEntityClass() {
        return Technician.class;
    }
}
