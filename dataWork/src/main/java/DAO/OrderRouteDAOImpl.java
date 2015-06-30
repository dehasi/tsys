package DAO;

import model.OrderRoute;
import model.statuses.OrderStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafa on 30.06.2015.
 */
public class OrderRouteDAOImpl extends GenericDAOImpl<OrderRoute> {
    public OrderRouteDAOImpl(Class<OrderRoute> clazz) {
        super(clazz);
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

    public Set<OrderRoute> getAllNotDoneOrders() {
        return null;
    }
}
