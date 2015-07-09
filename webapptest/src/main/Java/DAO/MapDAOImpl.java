package DAO;

import model.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.omg.PortableInterceptor.INACTIVE;
import utils.HibernateUtil;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Rafa on 09.07.2015.
 */
public class MapDAOImpl extends GenericDAOImpl<Map> {
    public MapDAOImpl(Class<Map> clazz) {
        super(clazz);
    }



    public Integer getDistance(int a, int b) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(Map.class)
                    .add(Restrictions.eq("cityA", a))
                    .add(Restrictions.eq("cityB", b));
            crit.setMaxResults(50);
            List<Map> l1 = crit.list();

            if (l1 == null) {
                 crit = session.createCriteria(Map.class)
                        .add(Restrictions.eq("cityA", b))
                        .add(Restrictions.eq("cityB", a));
                crit.setMaxResults(50);
                l1 = crit.list();
            }
            if (l1 == null){
                return null;
            }
            Map m = l1.get(0);
            return m.getDistance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
