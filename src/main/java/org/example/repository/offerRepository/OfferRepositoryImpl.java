package org.example.repository.offerRepository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.Offer;
import org.hibernate.SessionFactory;

public class OfferRepositoryImpl extends BaseRepositoryImpl<Offer, Long> implements OfferRepository {
    public OfferRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Offer> getEntityClass() {
        return Offer.class;
    }
}
