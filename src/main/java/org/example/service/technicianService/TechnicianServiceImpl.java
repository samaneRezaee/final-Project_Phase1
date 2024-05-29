package org.example.service.technicianService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.Technician;
import org.example.repository.technicianRepository.TechnicianRepository;
import org.hibernate.SessionFactory;

public class TechnicianServiceImpl extends BaseServiceImpl<Technician, Long, TechnicianRepository> implements TechnicianService {
    public TechnicianServiceImpl(TechnicianRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
