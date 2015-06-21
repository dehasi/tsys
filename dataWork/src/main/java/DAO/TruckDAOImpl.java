package DAO;

import model.Truck;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rafa on 20.06.2015.
 */
public class TruckDAOImpl implements TruckDAO{
    @Override
    public void addTruck(Truck truck) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(truck);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in addition truck");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateTruck(Long id, Truck truck) throws SQLException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.update(truck);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in updating truck");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Truck getTruckById(String id) throws SQLException {
        Truck truck = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            truck = session.get(Truck.class, id);
        } catch (Exception e) {
            System.out.println("Error getTruckById");
            System.out.println(e.getMessage());
        }
        return truck;
    }

    @Override
    public Collection getAllTrucks() throws SQLException {
        List trucks = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            trucks = session.createCriteria(Truck.class).list();

        }catch (Exception e) {
            System.out.println("Error 'getAllTrucks'");
            System.out.println(e.getMessage());
        }
        return trucks;
    }

    @Override
    public void deleteTruck(Truck truck) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.delete(truck);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in delete");
            System.out.println(e.getMessage());
        }
    }

    public void printAll() throws SQLException{
        List<Truck> trucks = (List<Truck>) getAllTrucks();

        for(Truck truck : trucks){
            System.out.println("id: " + truck.getId() + " status: " + truck.getStatus() +
                     " city: "  + truck.getCity().getName());
        }
    }
}
