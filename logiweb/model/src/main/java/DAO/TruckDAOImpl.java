package DAO;

import model.City;
import model.Truck;
import model.statuses.TruckStatus;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafa on 20.06.2015.
 */
public class TruckDAOImpl extends GenericDAOImpl<Truck>{

    public TruckDAOImpl(Class<Truck> clazz) {
        super(clazz);
    }

    public Set<Truck> getTrucksForOrder(int weight) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(Truck.class)
                    .add(Restrictions.ge("capacity", (long)weight))
                    .add(Restrictions.eq("status", TruckStatus.OK));

            crit.setMaxResults(50);
            List trucks = crit.list();
            return new HashSet<>(trucks);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Truck getTruckById(String id) {
        Truck t = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t =  session.load(Truck.class, id);
            Hibernate.initialize(t);
        } catch (Exception e) {
            System.out.println("Error getById");
            System.out.println(e.getMessage());
        }
        return t;
    }

    public Set<Truck> getTrucksByStatus(TruckStatus status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(Truck.class)
                    .add(Restrictions.eq("status", status));
            crit.setMaxResults(50);
            List trucks = crit.list();
            return new HashSet<>(trucks);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //   	���� �� ��������� � ������ ������ ������� �������;
    public Set<Truck> getFreeTrucks() {
        return null;
    }

    //   	���� �������� �� �����������
    // (� ������ ��������/�������� ������ � ������� �� �������� ����������);
    public Set<Truck> getFitTrucks(int weight, City city) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(Truck.class)
                    .add(Restrictions.ge("capacity", (long) weight))
                    .add(Restrictions.eq("status", TruckStatus.OK))
                    .add(Restrictions.eq("city", city));

            crit.setMaxResults(50);
            List trucks = crit.list();
            return new HashSet<>(trucks);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
