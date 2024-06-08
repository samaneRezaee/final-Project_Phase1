package org.example.service.technicianService;

import org.example.base.service.BaseServiceImpl;
import org.example.model.SubServe;
import org.example.model.Technician;
import org.example.model.enums.TechnicianStatus;
import org.example.repository.technicianRepository.TechnicianRepository;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.util.List;

public class TechnicianServiceImpl extends BaseServiceImpl<Technician, Long, TechnicianRepository> implements TechnicianService {
    public TechnicianServiceImpl(TechnicianRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public void loadAllTechnician() {
        try {
            List<Technician> technicians = repository.loadAllTechnician();
            for (Technician t : technicians) {
                System.out.println("id: " + t.getId() + "   " + "username: " + t.getUsername() + "   " + "skills: " + t.getSkills() + "   " + "status: " + t.getStatus());
            }
        } catch (HibernateException e) {
            System.out.println("TechnicianRepo" + e.getMessage());
        }
    }

    @Override
    public void showTechnicianWithStatus(TechnicianStatus status) {
        try {
            List<Technician> technicians = repository.showTechnicianWithStatus(status);
            for (Technician t : technicians) {
                System.out.println("id: " + t.getId() + "   " + "username: " + t.getUsername() + "   " + "skills: " + t.getSkills() + "   " + "status: " + t.getStatus());
            }
        } catch (HibernateException e) {
            System.out.println("TechnicianRepo" + e.getMessage());
        }
    }
}
