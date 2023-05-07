package dao;

import entity.Film;
import hibernate.HibernateSessionFactoryUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FilmDao {
    public static void update(Film film){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(film);

        session.getTransaction().commit();
        session.close();
    }

    public static void save(Film film){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(film);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(Film film){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(film);
        session.getTransaction().commit();
        session.close();
    }


    public static List<Film> getAllFilm(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        CriteriaBuilder crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Film> crQuery = crBuilder.createQuery(Film.class);
        Root<Film> root = crQuery.from(Film.class);
        crQuery.select(root);
        List<Film> list = session.createQuery(crQuery).list();
        trans.commit();
        session.close();
        return list;
    }
}
