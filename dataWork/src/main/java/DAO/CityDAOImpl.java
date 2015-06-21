package DAO;

import model.City;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rafa on 20.06.2015.
 */
public class CityDAOImpl implements CityDAO {

    @Override
    public void addCity(City city) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(city);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in addition");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateCity(Long id, City city) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.update(city);
            session.getTransaction().commit();
        }catch (Exception e) {
            System.out.println("Error in city updating");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public City getCityById(Long id) throws SQLException {
        City city = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            city =  session.get(City.class, id);
        } catch (Exception e) {
            System.out.println("Error getCargoById");
            System.out.println(e.getMessage());
        }
        return city;
    }

    @Override
    public Collection getAllCities() throws SQLException {
        List cities = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            cities = session.createCriteria(City.class).list();
        } catch (Exception e) {
            System.out.println("Error 'getAll'");
            System.out.println(e.getMessage());
        }
        return cities;
    }

    @Override
    public void deleteCity(City city) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.delete(city);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in delete");
            System.out.println(e.getMessage());
        }
    }
    public void printAll() throws SQLException{
        List<City> cities = (List<City>) getAllCities();

        for(City city : cities){
            System.out.println("id: " + city.getId() + " name: " + city.getName() + " comment: "  + city.getComment() );

        }
    }
}
