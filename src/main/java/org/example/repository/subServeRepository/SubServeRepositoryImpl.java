package org.example.repository.subServeRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.conncetion.SessionFactorySingleton;
import org.example.model.SubServe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SubServeRepositoryImpl extends BaseRepositoryImpl<SubServe, Long> implements SubServeRepository {
    public SubServeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<SubServe> getEntityClass() {
        return SubServe.class;
    }

    @Override
    public boolean isExistSubServe(String title) {
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hqlIsExistSubServe = "FROM SubServe ss WHERE ss.title = :title";
        Query<SubServe> subServeQuery = session.createQuery(hqlIsExistSubServe, SubServe.class);
        subServeQuery.setParameter("title", title);
        return subServeQuery.uniqueResult() != null;
    }

    @Override
    public List<SubServe> loadAllSubServe() {
        List<SubServe> subServeList=null;
        Session session = SessionFactorySingleton.getInstance().openSession();
        String hqlLoadAllSub = "FROM SubServe";
        Query<SubServe> subServeQueryAll=session.createQuery(hqlLoadAllSub, SubServe.class);
        subServeList=subServeQueryAll.getResultList();
        return subServeList;
    }
}
