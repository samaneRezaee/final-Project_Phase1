package org.example.utility;

import org.example.conncetion.SessionFactorySingleton;

import org.hibernate.SessionFactory;

public class ApplicationContext {
    private static final SessionFactory SESSION_FACTORY;


    static {
        SESSION_FACTORY = SessionFactorySingleton.getInstance();

    }



}
