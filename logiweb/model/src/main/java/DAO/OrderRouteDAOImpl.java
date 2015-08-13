package DAO;

import model.OrderRoute;
import model.statuses.DoneStatus;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import utils.HibernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafa on 30.06.2015.
 */
@Repository
@Transactional(propagation= Propagation.REQUIRED)
public class OrderRouteDAOImpl extends GenericDAOImpl<OrderRoute> implements OrderRouteDAO {
    public OrderRouteDAOImpl(Class<OrderRoute> clazz) {
        super(clazz);
    }
    public OrderRouteDAOImpl() {
        super();
    }


    /**
     public Set<Baggage> getBaggagesByStatus(BaggageStatus status) {
     try {
     CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
     CriteriaQuery<Baggage> criteriaQuery = criteriaBuilder.createQuery(Baggage.class);

     Root<Baggage> baggageRoot =criteriaQuery.from(Baggage.class);

     List<Baggage> bg = getEntityManager().createQuery(criteriaQuery.select(baggageRoot).where(criteriaBuilder.equal(
     baggageRoot.get("status"), status
     ))).getResultList();

     return new HashSet<>(bg);
     } catch (Exception e) {
     System.out.println(e.getMessage());
     }
     return null;
     }

     public int getMaxId() {
     try {
     TypedQuery<Integer> query =  getEntityManager().createNamedQuery("Baggage.getMaxId", Integer.class);
     return  query.getResultList().get(0);
     }catch (Exception e) {
     return -1;
     }

     }
     */

    @Override
    public Set<OrderRoute> getOrdersByStaus(DoneStatus status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(OrderRoute.class)
                    .add(Restrictions.eq("status", status));
            crit.setMaxResults(50);
            List trucks = crit.list();
            return new HashSet<>(trucks);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public Set<OrderRoute> getRouteByOrderId(int orderId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.getNamedQuery("OrderRoute.getRoute")
                    .setString("id", String.valueOf(orderId));
            return new HashSet<>(query.list());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public String getTruckId(int orderId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            Query query = session.getNamedQuery("OrderRoute.getTruck")
                    .setString("id", String.valueOf(orderId));
            String id = (String) query.list().get(0);
            return id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public OrderRoute getOrderByTruckId(String truckId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(OrderRoute.class)
                    .add(Restrictions.eq("truck", truckId));
            crit.setMaxResults(50);

            List trucks = crit.list();
            return (OrderRoute) new HashSet<>(trucks).iterator().next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Set<Integer> getAllOrderIds() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.getNamedQuery("OrderRoute.getAllId");
            return new HashSet<>(query.list());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public int getOrderStatus(int orderId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.getNamedQuery("OrderRoute.getOrderStatus")
                    .setString("order", String.valueOf(orderId));
            List stat = query.list();
            return (int) stat.get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int getMaxId() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.getNamedQuery("OrderRoute.maxId");
            return  (int)query.list().get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public Set<OrderRoute> getRoutesByBaggageId(int baggageId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(OrderRoute.class)
                    .add(Restrictions.eq("baggage", baggageId));
            crit.setMaxResults(50);

            List routes = crit.list();
            return new HashSet<>(routes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
