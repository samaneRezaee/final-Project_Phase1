package org.example.conncetion;

import org.example.model.*;
import org.example.model.enums.Role;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {

    private SessionFactorySingleton() {}

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Comment.class)
                    .addAnnotatedClass(Credit.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Offer.class)
                    .addAnnotatedClass(Request.class)
                    .addAnnotatedClass(Person.class)
                    .addAnnotatedClass(Serve.class)
                    .addAnnotatedClass(SubServe.class)
                    .addAnnotatedClass(Technician.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}