package org.example.base.service;

import lombok.RequiredArgsConstructor;
import org.example.base.entity.BaseEntity;
import org.example.base.repository.BaseRepository;
import org.example.exception.NotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;

@RequiredArgsConstructor
public class BaseServiceImpl<T extends BaseEntity<ID>,
        ID extends Serializable,
        R extends BaseRepository<T, ID>>
        implements BaseService<T, ID> {
    public final R repository;
    public final SessionFactory sessionFactory;

    @Override
    public T saveOrUpdate(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            T t = repository.saveOrUpdate(entity);
            transaction.commit();
            return t;
        } catch (HibernateException e) {
            assert transaction != null;
            return null;
        }
    }
    @Override
    public T findById(ID id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            T foundEntity = repository.findById(id).orElseThrow(
                    () -> new NotFoundException(String.format("entity with %s not found", id)));
            session.getTransaction().commit();
            return foundEntity;
        } catch (HibernateException e) {
            return null;
        }
    }

        @Override
        public void delete (T t){

            Transaction transaction = null;
            try (Session session = sessionFactory.getCurrentSession()) {
                transaction = session.beginTransaction();
                repository.delete(t);
                transaction.commit();
            } catch (Exception e) {
                assert transaction != null;
                transaction.rollback();
            }
        }
    }
