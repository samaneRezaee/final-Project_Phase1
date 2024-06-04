package org.example.repository.serveRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.conncetion.SessionFactorySingleton;
import org.example.model.Serve;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class ServeRepositoryImpl extends BaseRepositoryImpl<Serve, Long> implements ServeRepository {
    public ServeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Serve> getEntityClass() {
        return Serve.class;
    }

    @Override
    public boolean isExistServe(String title) {
        Session session= SessionFactorySingleton.getInstance().openSession();
        String hqlIsExistServe="FROM Serve s WHERE s.title = :title ";
        Query<Serve> serveQuery=session.createQuery(hqlIsExistServe, Serve.class);
        serveQuery.setParameter("title", title);
        return serveQuery.uniqueResult() != null;
    }
}
