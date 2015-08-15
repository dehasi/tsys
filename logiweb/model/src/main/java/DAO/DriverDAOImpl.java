package DAO;

import model.City;
import model.Driver;
import model.statuses.DriverStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Data access class for working with Driver entity
 */
@Repository
@Transactional(propagation= Propagation.REQUIRED)
public class DriverDAOImpl extends GenericDAOImpl<Driver> implements DriverDAO {
    Logger logger = Logger.getLogger(DriverDAOImpl.class);

    static final int MAX_WORK_HOURS = 176;

    public DriverDAOImpl(Class<Driver> clazz) {
        super(clazz);
    }
    public DriverDAOImpl() {
        super();
    }

    @Override
    public Set<Driver> getDriversByCity(City city){
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Driver> criteriaQuery = criteriaBuilder.createQuery(Driver.class);
            Root<Driver> driverRoot =criteriaQuery.from(Driver.class);
            List<Driver> drivers = getEntityManager().createQuery(criteriaQuery.select(driverRoot)
                    .where(criteriaBuilder.equal(driverRoot.get("city"), city )))
                    .getResultList();

            return new HashSet<>(drivers);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Set<Driver> getDriversByStatus(DriverStatus status) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Driver> criteriaQuery = criteriaBuilder.createQuery(Driver.class);
            Root<Driver> driverRoot = criteriaQuery.from(Driver.class);
            List<Driver> drivers = getEntityManager().createQuery(criteriaQuery.select(driverRoot)
                    .where(criteriaBuilder.equal(driverRoot.get("status"), status)))
                    .getResultList();

            return new HashSet<>(drivers);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Set<Driver> getDriversByOrderId(Long orderId) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Driver> criteriaQuery = criteriaBuilder.createQuery(Driver.class);
            Root<Driver> driverRoot = criteriaQuery.from(Driver.class);
            List<Driver> drivers = getEntityManager().createQuery(criteriaQuery.select(driverRoot).
                    where(criteriaBuilder.equal(driverRoot.get("orderRoute"), orderId
                    ))).getResultList();

            return new HashSet<>(drivers);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Set<Driver> getDriversForOrder(City city, int hoursWorked) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Driver> criteriaQuery = criteriaBuilder.createQuery(Driver.class);
            Root<Driver> driverRoot = criteriaQuery.from(Driver.class);
            List<Driver> drivers = getEntityManager().createQuery(criteriaQuery.select(driverRoot).
                    where(criteriaBuilder.isNull(driverRoot.get("orderRoute")),
                            criteriaBuilder.equal(driverRoot.get("city"), city),
                            criteriaBuilder.le(driverRoot.get("hoursWorked"),MAX_WORK_HOURS - hoursWorked )
                    )).getResultList();

            return new HashSet<>(drivers);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Set<Driver> getDriverFriends(Integer id) {
        try {
            TypedQuery<Driver> query =  getEntityManager().createNamedQuery("Driver.getDriverFriends", Driver.class);
            query.setParameter("did", id);
            return new HashSet<>(query.getResultList());
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Set<Driver> getInOrderDrivers() {
        try {
            TypedQuery<Driver> query =  getEntityManager().createNamedQuery("Driver.getInOrder", Driver.class);
            return new HashSet<>(query.getResultList());
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Set<Driver> getFreeDrivers() {
        try {
            TypedQuery<Driver> query =  getEntityManager().createNamedQuery("Driver.getFree", Driver.class);
            return new HashSet<>(query.getResultList());
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

}
