package DAO;

import model.Baggage;
import model.statuses.BaggageStatus;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import utils.HibernateUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafa on 25.06.2015.
 */
@Repository
public class BaggageDAOImpl extends  GenericDAOImpl<Baggage> {

    public BaggageDAOImpl(Class<Baggage> clazz) {
        super(clazz);
    }

    public BaggageDAOImpl() {
        super();
    }

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



}
