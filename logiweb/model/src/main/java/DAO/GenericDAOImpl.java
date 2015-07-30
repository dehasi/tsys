package DAO;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rafa on 25.06.2015.
 */
public class GenericDAOImpl<T> implements GeneticDAO<T> {
    Logger logger = Logger.getLogger(GenericDAOImpl.class);
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
            return ;
        } catch (Exception e) {
            logger.error("Error in addition");
            logger.error(e);
        }
        return ;
    }
    @Override
    public void update(T t) throws SQLException {
            try (Session session = HibernateUtil.getSessionFactory().openSession()){
                session.beginTransaction();
                session.update(t);
                session.getTransaction().commit();
            }catch (Exception e) {
                logger.error("Error in updating");
                logger.error(e);
            }
    }

    @Override
    public T getById(Integer id) throws SQLException {
        T t = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t =  session.load(clazz, id);
            Hibernate.initialize(t);
        } catch (Exception e) {
            logger.error("Error in getById");
            logger.error(e);
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
            logger.error("Error in getAll");
            logger.error(e);
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
            logger.error("Error in deleting");
            logger.error(e);
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
