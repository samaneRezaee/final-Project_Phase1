package org.example.service.subServeService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.Serve;
import org.example.model.SubServe;
import org.example.repository.subServeRepository.SubServeRepository;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.util.List;

public class SubServeServiceImpl extends BaseServiceImpl<SubServe, Long, SubServeRepository> implements SubServeService {
    public SubServeServiceImpl(SubServeRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public boolean isExistSubServe(String title) {
        try {
            return repository.isExistSubServe(title);
        }catch (HibernateException e){
            System.out.println("SubServeRepoImpl" + e.getMessage());
        }
        return false;
    }

    @Override
    public void loadAllSubServe() {
        try {
            List<SubServe> subServeList = repository.loadAllSubServe();
            for (SubServe s : subServeList) {
                System.out.println("id: " + s.getId() + "   " + "title: " + s.getTitle()+ "   " + "price: " + s.getPrice() + "   " + "description: " + s.getDescription());
            }
        } catch (HibernateException e) {
            System.out.println("ServeRepoImpl" + e.getMessage());
        }
    }

    @Override
    public void findByServeId(Long id) {
        try {
            List<SubServe> subServeList = repository.findByServeId(id);
            for (SubServe s : subServeList) {
                System.out.println("id: " + s.getId() + "   " + "title: " + s.getTitle() + "   " + "price: " + s.getPrice() + "   " + "description: " + s.getDescription());
            }
        } catch (HibernateException e) {
            System.out.println("ServeRepoImpl" + e.getMessage());
        }
    }
}
