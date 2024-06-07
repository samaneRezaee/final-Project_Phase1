package org.example.service.serveService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.Serve;
import org.example.repository.serveRepository.ServeRepository;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.util.List;

public class ServeServiceImpl extends BaseServiceImpl<Serve, Long, ServeRepository> implements ServeService {
    public ServeServiceImpl(ServeRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public boolean isExistServe(String title) {
        try {
            return repository.isExistServe(title);
        } catch (HibernateException e) {
            System.out.println("ServeRepoImpl" + e.getMessage());
        }
        return false;
    }

    @Override
    public void loadAllServe() {
        try {
            List<Serve> serveList = repository.loadAllServe();
            for (Serve s : serveList) {
                System.out.println("id: " + s.getId() + "   " + "title: " + s.getTitle() + "   " + "description: " + s.getDescription());
            }
        } catch (HibernateException e) {
            System.out.println("ServeRepoImpl" + e.getMessage());
        }
    }
}
