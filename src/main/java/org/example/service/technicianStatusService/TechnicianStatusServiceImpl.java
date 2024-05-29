package org.example.service.technicianStatusService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.TechnicianStatus;
import org.example.repository.technicianStatusRepository.TechnicianStatusRepository;
import org.hibernate.SessionFactory;

public class TechnicianStatusServiceImpl extends BaseServiceImpl<TechnicianStatus, Long, TechnicianStatusRepository> implements TechnicianStatusService {
    public TechnicianStatusServiceImpl(TechnicianStatusRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
