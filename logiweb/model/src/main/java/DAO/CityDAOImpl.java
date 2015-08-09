package DAO;

import model.City;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import utils.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Rafa on 20.06.2015.
 */
@Repository
public class CityDAOImpl extends GenericDAOImpl<City> {

    public CityDAOImpl(Class<City> clazz) {
        super(clazz);
    }
    public CityDAOImpl() {
        super();
    }

    public City getCityByName(String name) {
        try{

            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<City> criteriaQuery = criteriaBuilder.createQuery(City.class);

            Root<City> baggageRoot =criteriaQuery.from(City.class);

            List<City> cities = getEntityManager().createQuery(criteriaQuery.select(baggageRoot).where(criteriaBuilder.equal(
                    baggageRoot.get("name"), name
            ))).getResultList();

            return cities.get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
