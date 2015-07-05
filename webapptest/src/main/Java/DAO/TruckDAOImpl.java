package DAO;

import model.Truck;
import model.statuses.TruckStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.internal.EntityManagerImpl;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLException;
import java.util.*;

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

//    //   	фура находится в исправном состоянии;
//    public Set<Truck> getOKTrucks() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()){
//            Criteria crit = session.createCriteria(Truck.class)
//                    .add(Restrictions.eq("status", TruckStatus.OK));
//            crit.setMaxResults(50);
//            List trucks = crit.list();
//            return new HashSet<>(trucks);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }


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

    //   	фура не выполняет в данный момент никаких заказов;
    public Set<Truck> getFreeTrucks() {
        return null;
    }

    //   	фура подходит по вместимости
    // (с учетом погрузки/выгрузки грузов в городах по маршруту следования);
    public Set<Truck> getFitTrucks(int weight) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(Truck.class)
                    .add(Restrictions.ge("capacity", (long) weight));
            crit.setMaxResults(50);
            List trucks = crit.list();
            return new HashSet<>(trucks);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
