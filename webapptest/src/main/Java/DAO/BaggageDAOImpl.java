package DAO;

import model.Baggage;
import model.statuses.BaggageStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Rafa on 25.06.2015.
 */
public class BaggageDAOImpl extends  GenericDAOImpl<Baggage> {

    public BaggageDAOImpl(Class<Baggage> clazz) {
        super(clazz);
    }

    public Set<Baggage> getBaggagesByStatus(BaggageStatus status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(Baggage.class)
                    .add(Restrictions.eq("status", status));
            crit.setMaxResults(50);
            List trucks = crit.list();
            return new HashSet<>(trucks);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
