package DAO;

import model.City;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Rafa on 20.06.2015.
 */
public class CityDAOImpl extends GenericDAOImpl<City> {

    public CityDAOImpl(Class<City> clazz) {
        super(clazz);
    }

    public City getCityByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(City.class)
                    .add(Restrictions.eq("name", name));
            crit.setMaxResults(50);
            return (City) crit.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
