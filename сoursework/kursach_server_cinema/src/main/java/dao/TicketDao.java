package dao;

import entity.Ticket;
import entity.Timetable;
import hibernate.HibernateSessionFactoryUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketDao {
    public static void update(Ticket ticket){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(ticket);

        session.getTransaction().commit();
        session.close();
    }

    public static void save(Ticket ticket){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(ticket);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(Ticket ticket){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(ticket);
        session.getTransaction().commit();
        session.close();
    }

    public static List<Ticket> getAllTickets() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        CriteriaBuilder crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> crQuery = crBuilder.createQuery(Ticket.class);
        Root<Ticket> root = crQuery.from(Ticket.class);
        crQuery.select(root);
        List<Ticket> list = session.createQuery(crQuery).list();
        trans.commit();
        session.close();
        return list;
    }
}
