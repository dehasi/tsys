package DAO;

import model.Driver;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rafa on 21.06.2015.
 */
public class DriverDAOImpl implements DriverDAO{
    @Override
    public void addDriver(Driver driver) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(driver);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in addition truck");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateDriver(Long id, Driver driver) throws SQLException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.update(driver);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in updating truck");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Driver getDriverById(String id) throws SQLException {

        Driver driver = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            driver = session.get(Driver.class, id);
        } catch (Exception e) {
            System.out.println("Error getDriverById");
            System.out.println(e.getMessage());
        }
        return driver;
    }

    @Override
    public Collection getAllDrivers() throws SQLException {
        List drivers = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            drivers = session.createCriteria(Driver.class).list();

        }catch (Exception e) {
            System.out.println("Error 'getAllDrivers'");
            System.out.println(e.getMessage());
        }
        return drivers;
    }

    @Override
    public void deleteDriver(Driver driver) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.delete(driver);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in delete");
            System.out.println(e.getMessage());
        }
    }
    public void printAll() throws SQLException {
        List<Driver> drivers = (List<Driver>) getAllDrivers();

        for(Driver driver : drivers){
            System.out.println("id: " + driver.getId() +
                    " name: " + driver.getName() +
                    " last name: " + driver.getLastName() +
                    " worked hours: " + driver.getWorked() +
                    " status: " + driver.getStatus() +
                    " city: " + driver.getCity().getName() + " truck: " + driver.getTruck().getId());
        }
    }
}
