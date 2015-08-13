package DAO;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rafa on 25.06.2015.
 */

@Transactional(propagation= Propagation.REQUIRED)
public abstract class GenericDAOImpl<T> implements GeneticDAO<T> {
    Logger logger = Logger.getLogger(GenericDAOImpl.class);

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    private  Class<T> clazz;

    @PersistenceContext
    @Autowired
    protected EntityManager entityManager;

    GenericDAOImpl(Class<T> clazz){
        this.clazz = clazz;
    }

    public Class<T> getInterfaceClass() {
        return clazz;
    }

    @Override
    public void add(T t) throws SQLException {
        try {
            entityManager.persist(t);
            entityManager.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error("Error in addition");
            logger.error(e);
        }

    }
    @Override

    public void update(T t) throws SQLException {
        try {
            entityManager.merge(t);
            entityManager.flush();
        } catch (Exception e) {
            throw e;
//            System.out.println(e.getMessage());
//                logger.error("Error in updating");
//                logger.error(e);
            }
    }

    @Override
    public T getById(Integer id) throws SQLException {

        try  {
            return getEntityManager().find(clazz, id);
        } catch (Exception e) {
            logger.error("Error in getById");
            logger.error(e);
            return null;
        }
    }

    @Override
    public Collection getAll() throws SQLException {
        List ts = new ArrayList<T>();
        T t;
        try {
            //EntityManager em = getEntityManager();
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
            Root<T> tRoot = query.from(clazz);
            return entityManager.createQuery(query.select(tRoot)).getResultList();
        }  catch (Exception e) {
            logger.error("Error in getAll");
            logger.error(e);
        }
        return ts;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(T t) throws SQLException {
        t = entityManager.merge(t); // merge and assign a to the attached entity
        entityManager.remove(t); //

        try {
            getEntityManager().remove(t);
        } catch (Exception e) {
            throw e;
//            logger.error("Error in deleting");
//            logger.error(e);
        }
    }

    @Override
    public void printAll() throws SQLException {
        List<T> ts = (List<T>) getAll();

        for(T t : ts){
            System.out.println(t );

        }
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //    @Transactional
    public EntityManager getEntityManager() {
        return entityManager;
    }
    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        clazz = (Class) pt.getActualTypeArguments()[0];
    }


}
