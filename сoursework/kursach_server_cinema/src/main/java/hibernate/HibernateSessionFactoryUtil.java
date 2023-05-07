package hibernate;

import entity.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        try{
            //Создаем SessionFactory
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(LoginKeys.class);
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Film.class);
            configuration.addAnnotatedClass(Place.class);
            configuration.addAnnotatedClass(Ticket.class);
            configuration.addAnnotatedClass(Timetable.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory session = configuration.buildSessionFactory(builder.build());
            System.out.println("Database connected");
            return session;
        } catch (HibernateException e) {
            System.out.println("Database is not connected");
            throw new RuntimeException(e);
        }
    }
    public static SessionFactory getSessionFactory() {return sessionFactory;}

    public static void shutdown(){
        getSessionFactory().close();
    }
    }
