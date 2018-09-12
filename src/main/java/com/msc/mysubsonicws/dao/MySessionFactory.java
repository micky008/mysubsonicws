package com.msc.mysubsonicws.dao;

import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Michael
 */
public class MySessionFactory {

    private static SessionFactory sessionFactory;

    public static void setUp()  {
        
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure(new File("hibernate-dev.cfg.xml")).build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    
    public synchronized static SessionFactory getInstance(){
        if (sessionFactory == null){
            setUp();
        }
        return sessionFactory;
    }

}
