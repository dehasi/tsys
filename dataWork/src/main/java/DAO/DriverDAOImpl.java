package DAO;

import model.City;
import model.Driver;
import model.statuses.DriverStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Rafa on 21.06.2015.
 */
public class DriverDAOImpl extends GenericDAOImpl<Driver> {
    final int MAX_WORK_HOURS = 176;

    public DriverDAOImpl(Class<Driver> clazz) {
        super(clazz);
    }

    public Set<Driver> getDriversByCity(City city){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(Driver.class)
                    .add(Restrictions.eq("city", city.getId()));
            crit.setMaxResults(50);
            List<Driver> drivers = crit.list();

            return new HashSet<>(drivers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Set<Driver> getDriversByStatus(DriverStatus status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(Driver.class)
                    .add(Restrictions.eq("status", status));
            crit.setMaxResults(50);
            List<Driver> drivers = crit.list();

            return new HashSet<>(drivers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Set<Driver> getDriversByOrderId(Long orderId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(Driver.class)
                    .add(Restrictions.eq("order_id", orderId));
            crit.setMaxResults(50);
            List<Driver> drivers = crit.list();

            return new HashSet<>(drivers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Set<Driver> getFreeDrivers() {
        return getDriversByOrderId(null);
    }

    public Set<Driver> getDriversForOrder(City city, int hoursWorked) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(Driver.class)
                    .add(Restrictions.eq("order_id", null))
                    .add(Restrictions.eq("city", city.getId()))
                    .add(Restrictions.le("hoursWorked", MAX_WORK_HOURS - hoursWorked));
            crit.setMaxResults(50);
            List<Driver> drivers = crit.list();

            return new HashSet<>(drivers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
