package org.example.service.subServeService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.SubServe;
import org.example.repository.subServeRepository.SubServeRepository;
import org.hibernate.SessionFactory;

public class SubServeServiceImpl extends BaseServiceImpl<SubServe, Long, SubServeRepository> implements SubServeService {
    public SubServeServiceImpl(SubServeRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
