package DAO;

import model.OrderRoute;
import model.Truck;
import model.statuses.DoneStatus;
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
 *  implemantation orderRouteDAO
 */
@Repository
@Transactional(propagation= Propagation.REQUIRED)
public class OrderRouteDAOImpl extends GenericDAOImpl<OrderRoute> implements OrderRouteDAO {
    Logger logger = Logger.getLogger(OrderRouteDAOImpl.class);

    public OrderRouteDAOImpl(Class<OrderRoute> clazz) {
        super(clazz);
    }
    public OrderRouteDAOImpl() {
        super();
    }

    @Override
    public Set<OrderRoute> getOrdersByStatus(DoneStatus status) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<OrderRoute> criteriaQuery = criteriaBuilder.createQuery(OrderRoute.class);
            Root<OrderRoute> routeRoot = criteriaQuery.from(OrderRoute.class);
            List<OrderRoute> routes = getEntityManager().createQuery(criteriaQuery.select(routeRoot)
                    .where(criteriaBuilder.equal(routeRoot.get("orderStatus"), status)))
                    .getResultList();
            return new HashSet<>(routes);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Set<OrderRoute> getRouteByOrderId(int orderId) {
        try {
            TypedQuery<OrderRoute> query =  getEntityManager()
                    .createNamedQuery("OrderRoute.getRoute", OrderRoute.class);
            query.setParameter("oid", orderId);
            return new HashSet<>(query.getResultList());
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }


    @Override
    public String getTruckId(int orderId) {
        try {
            TypedQuery<Truck> query =  getEntityManager()
                    .createNamedQuery("OrderRoute.getTruck", Truck.class);
            query.setParameter("oid", orderId);
            return query.getResultList().get(0).getId();
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public OrderRoute getOrderByTruck(Truck truck) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<OrderRoute> criteriaQuery = criteriaBuilder.createQuery(OrderRoute.class);
            Root<OrderRoute> routeRoot = criteriaQuery.from(OrderRoute.class);
            List<OrderRoute> routes = getEntityManager().createQuery(criteriaQuery.select(routeRoot)
                    .where(criteriaBuilder.equal(routeRoot.get("truck"), truck)))
                    .getResultList();
            return routes.get(0);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Set<Integer> getAllOrderIds() {
        try {
            TypedQuery<Integer> query =  getEntityManager()
                    .createNamedQuery("OrderRoute.getAllId", Integer.class);
            return new HashSet<>(query.getResultList());
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public int getOrderStatus(int orderId) {
        try {
            TypedQuery<DoneStatus> query =  getEntityManager()
                    .createNamedQuery("OrderRoute.getOrderStatus", DoneStatus.class);
            query.setParameter("oid", orderId);

            return  query.getResultList().get(0) == DoneStatus.DONE?1:0;
        } catch (Exception e) {
            logger.error(e);
        }
        return 0;
    }

    @Override
    public int getMaxId() {
        try {
            TypedQuery<Integer> query =  getEntityManager()
                    .createNamedQuery("OrderRoute.maxId", Integer.class);
            return  query.getResultList().get(0);
        } catch (Exception e) {
            logger.error(e);
        }
        return -1;
    }

    @Override
    public int getMaxOrderId() {
        try {
            TypedQuery<Integer> query =  getEntityManager()
                    .createNamedQuery("OrderRoute.maxOrderId", Integer.class);
            return  query.getResultList().get(0);
        } catch (Exception e) {
            logger.error(e);
        }
        return -1;
    }

    @Override
    public Set<OrderRoute> getRoutesByBaggageId(int baggageId) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<OrderRoute> criteriaQuery = criteriaBuilder.createQuery(OrderRoute.class);
            Root<OrderRoute> routeRoot = criteriaQuery.from(OrderRoute.class);
            List<OrderRoute> routes = getEntityManager().createQuery(criteriaQuery.select(routeRoot)
                    .where(criteriaBuilder.equal(routeRoot.get("baggage"), baggageId)))
                    .getResultList();
            return new HashSet<>(routes);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

}
