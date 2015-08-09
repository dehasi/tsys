package DAO;

import model.OrderRoute;
import model.statuses.OrderStatus;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import utils.HibernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafa on 30.06.2015.
 */
@Repository
public class OrderRouteDAOImpl extends GenericDAOImpl<OrderRoute> {
    public OrderRouteDAOImpl(Class<OrderRoute> clazz) {
        super(clazz);
    }
    public OrderRouteDAOImpl() {
        super();
    }

    public Set<OrderRoute> getOrdersByStaus(OrderStatus status) {
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

    public Set<Integer> getAllOrderIds() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.getNamedQuery("OrderRoute.getAllId");
            return new HashSet<>(query.list());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

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

    public int getMaxId() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.getNamedQuery("OrderRoute.maxId");
            return  (int)query.list().get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

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
