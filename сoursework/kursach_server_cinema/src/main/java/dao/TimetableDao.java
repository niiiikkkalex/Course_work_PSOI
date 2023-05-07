package dao;

import entity.Film;
import entity.Timetable;
import hibernate.HibernateSessionFactoryUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TimetableDao {
    public static void update(Timetable timetable){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(timetable);

        session.getTransaction().commit();
        session.close();
    }

    public static void save(Timetable timetable){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(timetable);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(Timetable timetable){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(timetable);
        session.getTransaction().commit();
        session.close();
    }

    public static List<Timetable> getAllTimetable(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        CriteriaBuilder crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Timetable> crQuery = crBuilder.createQuery(Timetable.class);
        Root<Timetable> root = crQuery.from(Timetable.class);
        crQuery.select(root);
        List<Timetable> list = session.createQuery(crQuery).list();
        trans.commit();
        session.close();
        return list;
    }
}
