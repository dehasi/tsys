package DAO;

import model.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *  Class. Implements Map DAO.
 */

@Repository
@Transactional(propagation= Propagation.REQUIRED)
public class MapDAOImpl extends GenericDAOImpl<Map> implements MapDAO {

    public MapDAOImpl(Class<Map> clazz) { super(clazz); }
    public MapDAOImpl() { super(); }

    @Override
    public Integer getDistance(int a, int b) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Map> criteriaQuery = criteriaBuilder.createQuery(Map.class);
            Root<Map> mapRoot =criteriaQuery.from(Map.class);
            List<Map> maps = getEntityManager().createQuery(criteriaQuery.select(mapRoot)
                    .where(criteriaBuilder.equal(mapRoot.get("cityA"), a ),
                            criteriaBuilder.equal(mapRoot.get("cityB"), b)))
                    .getResultList();

            Map m = maps.get(0);
            return m.getDistance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
