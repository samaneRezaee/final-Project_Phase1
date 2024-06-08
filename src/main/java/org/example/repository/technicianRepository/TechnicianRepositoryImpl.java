package org.example.repository.technicianRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.conncetion.SessionFactorySingleton;
import org.example.model.SubServe;
import org.example.model.Technician;
import org.example.model.enums.TechnicianStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class TechnicianRepositoryImpl extends BaseRepositoryImpl<Technician, Long> implements TechnicianRepository {
    public TechnicianRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Technician> getEntityClass() {
        return Technician.class;
    }

    @Override
    public List<Technician> loadAllTechnician() {
        List<Technician> technicians = null;
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hqlLoadAllTechnician = "FROM Technician";
        Query<Technician> technicianQueryAll=session.createQuery(hqlLoadAllTechnician, Technician.class);
        technicians=technicianQueryAll.getResultList();
        return technicians;
    }

    @Override
    public List<Technician> showTechnicianWithStatus(TechnicianStatus status) {
        List<Technician> technicianList = null;
        Session session= SessionFactorySingleton.getInstance().openSession();
        String hqlTechnician="FROM Technician t WHERE t.status = :status";
        Query<Technician> technicianQuery=session.createQuery(hqlTechnician, Technician.class);
        technicianQuery.setParameter("status", status);
        technicianList=technicianQuery.getResultList();
        return technicianList;
    }
}
