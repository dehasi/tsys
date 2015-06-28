package DAO;

import model.Truck;
import org.hibernate.Session;
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
    public Set<Truck> getTrucksForOrger() {
        Set<Truck> trucks = new HashSet<>();
        //intersetion
        return null;
    }

    //   	фура находится в исправном состоянии;
    public Set<Truck> getOKTrucks() {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
HibernateUtil.getSessionFactory().getCurrentSession().createCriteria("");

        return null;
    }

    //   	фура не выполняет в данный момент никаких заказов;
    public Set<Truck> getFreeTrucks() {
        return null;
    }

    //   	фура подходит по вместимости
    // (с учетом погрузки/выгрузки грузов в городах по маршруту следования);
    public Set<Truck> getFitTrucks(int weight) {
        return null;
    }


}
