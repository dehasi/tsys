package DAO;

import model.Baggage;
import model.statuses.BaggageStatus;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import utils.HibernateUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
@Transactional(propagation= Propagation.REQUIRED)
public class BaggageDAOImpl extends  GenericDAOImpl<Baggage> implements BaggageDAO {

    public BaggageDAOImpl(Class<Baggage> clazz) {
        super(clazz);
    }

    public BaggageDAOImpl() {
        super();
    }

    @Override
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

    @Override
    public int getMaxId() {
        try {
            TypedQuery<Integer> query =  getEntityManager().createNamedQuery("Baggage.getMaxId", Integer.class);
            return  query.getResultList().get(0);
        }catch (Exception e) {
            return -1;
        }

    }



}
