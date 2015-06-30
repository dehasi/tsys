package DAO;

import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.Entity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rafa on 25.06.2015.
 */
public class GenericDAOImpl<T> implements GeneticDAO<T>{

    private final Class<T> clazz;

    GenericDAOImpl(Class<T> clazz){
        this.clazz = clazz;
    }

    public Class<T> getInterfaceClass() {
        return clazz;
    }

    @Override
    public void add(T t) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (Exception e) {
                System.out.println("Error in addition");
                System.out.println(e.getMessage());
        }
    }
    @Override
    public void update(T t) throws SQLException {
            try (Session session = HibernateUtil.getSessionFactory().openSession()){
                session.beginTransaction();
                session.update(t);
                session.getTransaction().commit();
            }catch (Exception e) {
                System.out.println("Error in city updating");
                System.out.println(e.getMessage());
            }
    }

    @Override
    public T getById(Integer id) throws SQLException {
        T t = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t =  session.get(clazz, id);
        } catch (Exception e) {
            System.out.println("Error getById");
            System.out.println(e.getMessage());
        }
        return t;
    }
//
    @Override
    public Collection getAll() throws SQLException {
        List ts = new ArrayList<T>();
        T t;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            ts = session.createCriteria(clazz).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ts;
    }

    @Override
    public void delete(T t) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void printAll() throws SQLException {
        List<T> ts = (List<T>) getAll();

        for(T t : ts){
            System.out.println(t );

        }
    }
}
