package DAO;

import model.City;
import model.Truck;
import model.statuses.TruckStatus;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import utils.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafa on 20.06.2015.
 */
@Repository
public class TruckDAOImpl extends GenericDAOImpl<Truck>{

    public TruckDAOImpl(Class<Truck> clazz) {
        super(clazz);
    }
    public TruckDAOImpl() {
        super();
    }

    public Set<Truck> getTrucksForOrder(int weight) {

        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Truck> criteriaQuery = criteriaBuilder.createQuery(Truck.class);

            Root<Truck> truckRoot =criteriaQuery.from(Truck.class);

            List<Truck> bg = getEntityManager().createQuery(criteriaQuery.select(truckRoot).where(criteriaBuilder.equal(
                    truckRoot.get("status"),TruckStatus.OK
            ), criteriaBuilder.ge(truckRoot.get("capacity"),(long)weight)
            )).getResultList();

            return new HashSet<>(bg);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Truck getTruckById(String id) {
        Truck t = null;
        try  {
            return getEntityManager().find(Truck.class, id);
        } catch (Exception e) {
            System.out.println("Error getById");
            System.out.println(e.getMessage());
        }
        return t;
    }

    public Set<Truck> getTrucksByStatus(TruckStatus status) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Truck> criteriaQuery = criteriaBuilder.createQuery(Truck.class);

            Root<Truck> truckRoot =criteriaQuery.from(Truck.class);

            List<Truck> bg = getEntityManager().createQuery(criteriaQuery.select(truckRoot).where(criteriaBuilder.equal(
                            truckRoot.get("status"), status)
            )).getResultList();

            return new HashSet<>(bg);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



    public Set<Truck> getFitTrucks(int weight, City city) {

        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Truck> criteriaQuery = criteriaBuilder.createQuery(Truck.class);

            Root<Truck> truckRoot =criteriaQuery.from(Truck.class);

            List<Truck> bg = getEntityManager().createQuery(criteriaQuery.select(truckRoot).where(criteriaBuilder.equal(
                            truckRoot.get("status"), TruckStatus.OK),
                    criteriaBuilder.equal(truckRoot.get("city"), city),
                    criteriaBuilder.ge(truckRoot.get("capacity"), (long) weight)
            )).getResultList();
            return new HashSet<>(bg);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
