package dao;


import entity.LoginKeys;
import hibernate.HibernateSessionFactoryUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LoginKeysDao {
    public static void update(LoginKeys keys){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(keys);
        session.getTransaction().commit();
        session.close();
    }

    public static void save(LoginKeys keys){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(keys);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(LoginKeys keys){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(keys);
        session.getTransaction().commit();
        session.close();
    }

    public static List<LoginKeys> getAllAdmin(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        CriteriaBuilder crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<LoginKeys> crQuery = crBuilder.createQuery(LoginKeys.class);
        Root<LoginKeys> root = crQuery.from(LoginKeys.class);
        crQuery.select(root);
        List<LoginKeys> list = session.createQuery(crQuery).list();
        trans.commit();
        session.close();
        return list;
    }
}
