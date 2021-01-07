package com.taskmanagement.util;

import com.taskmanagement.model.Project;
import com.taskmanagement.model.Task;
import com.taskmanagement.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
//        // JPA and Hibernate SessionFactory example
//        EntityManagerFactory emf =
//                Persistence.createEntityManagerFactory("task-management");
//        EntityManager entityManager = emf.createEntityManager();
//        // Get the Hibernate Session from the EntityManager in JPA
//        Session session = entityManager.unwrap(org.hibernate.Session.class);
//        SessionFactory factory = session.getSessionFactory();
//        return factory;
//          --------------------------------------------------------
        // Hibernate 5.4 SessionFactory example without XML
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
        settings.put("dialect", "org.hibernate.dialect.MySQL8Dialect");
        settings.put("hibernate.connection.url",
                "jdbc:mysql://localhost:3306/ltw_proiect");
        settings.put("hibernate.connection.username", "root");
        settings.put("hibernate.connection.password", "Parola.123");
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(User.class);
        metadataSources.addAnnotatedClass(Project.class);
        metadataSources.addAnnotatedClass(Task.class);
        Metadata metadata = metadataSources.buildMetadata();

        // here we build the SessionFactory (Hibernate 5.4)
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        // Session session = sessionFactory.getCurrentSession();
        return sessionFactory;
    }
}
