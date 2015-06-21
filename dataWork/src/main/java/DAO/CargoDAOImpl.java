/**
 * Created by Rafa on 19.06.2015.
 */

package DAO;

import model.Cargo;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CargoDAOImpl implements CargoDAO {

    public void addCargo(Cargo cargo) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(cargo);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in addition");
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void updateCargo(Long id, Cargo cargo) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(cargo);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error updating");
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public Cargo getCargoById(Long id) throws SQLException {
        Session session = null;
        Cargo cargo = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cargo =  session.get(Cargo.class, id); // (Cargo)
        } catch (Exception e) {
            System.out.println("Error getCargoById");
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cargo;
    }

    public Collection getAllCargos() throws SQLException {
        Session session = null;
        List cargos = new ArrayList<Cargo>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cargos = session.createCriteria(Cargo.class).list();
        } catch (Exception e) {
            System.out.println("Œ¯Ë·Í‡ 'getAll'");
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cargos;
    }

    public void deleteCargo(Cargo cargo) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(cargo);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in delete");
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void printAll() throws SQLException{
        List<Cargo> cargos = (List<Cargo>) getAllCargos();

        for(Cargo cargo : cargos){
            System.out.println("id: " + cargo.getId() + " name: " + cargo.getName() + " weight: "  + cargo.getWeight() + " status: " + cargo.getStatus() );

        }
    }
}
