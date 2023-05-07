package dao;

import entity.Client;
import entity.LoginKeys;
import hibernate.HibernateSessionFactoryUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDao {
    public static void update(Client cl){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(cl);

        session.getTransaction().commit();
        session.close();
    }

    public static void save(Client cl){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(cl);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(Client cl){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(cl);
        session.getTransaction().commit();
        session.close();
    }

    public static List<Client> getAllClient(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        CriteriaBuilder crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Client> crQuery = crBuilder.createQuery(Client.class);
        Root<Client> root = crQuery.from(Client.class);
        crQuery.select(root);
        List<Client> list = session.createQuery(crQuery).list();
        trans.commit();
        session.close();
        return list;
    }

    public Client findClientById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, id);
    }

    public static Client findClientByIdKeys(int id) {
        Client cl = new Client();
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT DISTINCT e from Client e inner join e.id_keys t where t.id_keys = :id");
            query.setParameter("id",id);
            cl = (Client) query.getSingleResult();
            session.close();
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
        }
        return cl;
    }
}
