package DAO;

import model.City;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Implements CityDAO interface
 */
@Repository
public class CityDAOImpl extends GenericDAOImpl<City> implements CityDAO {
    Logger logger = Logger.getLogger(CityDAOImpl.class);
    public CityDAOImpl(Class<City> clazz) {
        super(clazz);
    }
    public CityDAOImpl() {
        super();
    }

    @Override
    public City getCityByName(String name) {
        try{
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<City> criteriaQuery = criteriaBuilder.createQuery(City.class);
            Root<City> baggageRoot =criteriaQuery.from(City.class);
            List<City> cities = getEntityManager().createQuery(criteriaQuery.select(baggageRoot)
                    .where(criteriaBuilder.equal(baggageRoot.get("name"), name)))
                    .getResultList();
            return cities.get(0);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

}
