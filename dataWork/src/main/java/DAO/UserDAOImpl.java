package DAO;

import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;

import java.util.List;

/**
 * Created by Rafa on 29.06.2015.
 */
public class UserDAOImpl extends  GenericDAOImpl<User>{

    UserDAOImpl(Class<User> clazz) {
        super(clazz);
    }

    public Long getUserPasswordHash(String userName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(User.class)
                    .add(Restrictions.eq("name", userName));
            crit.setMaxResults(50);
            List<User> users = crit.list();
            if (users == null) return null;


//            List trucks = crit.list();


        } catch (Exception e) {
            System.out.println("Error in addition");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
