package DAO;

import model.City;
import model.Truck;
import model.statuses.TruckStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional(propagation= Propagation.REQUIRED)
public class TruckDAOImpl extends GenericDAOImpl<Truck> implements TruckDAO {

    Logger logger = Logger.getLogger(TruckDAOImpl.class);

    public TruckDAOImpl(Class<Truck> clazz) {
        super(clazz);
    }

    public TruckDAOImpl() {
        super();
    }

    @Override
    public Set<Truck> getTrucksForOrder(int weight) {

        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Truck> criteriaQuery = criteriaBuilder.createQuery(Truck.class);
            Root<Truck> truckRoot =criteriaQuery.from(Truck.class);
            List<Truck> bg = getEntityManager().createQuery(criteriaQuery.select(truckRoot)
                    .where(criteriaBuilder.equal(truckRoot.get("status"), TruckStatus.OK),
                            criteriaBuilder.ge(truckRoot.get("capacity"), (long) weight)))
                    .getResultList();
            return new HashSet<>(bg);

        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Truck getTruckById(String id) {
        try  {
            return getEntityManager().find(Truck.class, id);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Set<Truck> getTrucksByStatus(TruckStatus status) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Truck> criteriaQuery = criteriaBuilder.createQuery(Truck.class);
            Root<Truck> truckRoot =criteriaQuery.from(Truck.class);
            List<Truck> bg = getEntityManager().createQuery(criteriaQuery.select(truckRoot)
                    .where(criteriaBuilder.equal(truckRoot.get("status"), status)))
                    .getResultList();
            return new HashSet<>(bg);

        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Set<Truck> getFitTrucks(int weight, City city) {

        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Truck> criteriaQuery = criteriaBuilder.createQuery(Truck.class);
            Root<Truck> truckRoot =criteriaQuery.from(Truck.class);
            List<Truck> bg = getEntityManager().createQuery(criteriaQuery.select(truckRoot)
                    .where(criteriaBuilder.equal(truckRoot.get("status"), TruckStatus.OK),
                            criteriaBuilder.equal(truckRoot.get("city"), city),
                            criteriaBuilder.ge(truckRoot.get("capacity"), (long) weight)))
                    .getResultList();
            return new HashSet<>(bg);

        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

}
