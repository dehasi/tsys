package DAO;

import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import utils.HibernateUtil;

/**
 * Created by Rafa on 29.06.2015.
 */
@Repository
@Transactional(propagation= Propagation.REQUIRED)
public class UserDAOImpl extends  GenericDAOImpl<User> implements UserDAO {

    public UserDAOImpl(Class<User> clazz) {
        super(clazz);
    }
    public UserDAOImpl() {
        super();
    }

    @Override
    public Long getUserPasswordHash(String login) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(User.class)
                    .add(Restrictions.eq("login", login));
            crit.setMaxResults(50);
            User user = (User) crit.uniqueResult();
            if (user == null) return 0L;
            return user.getPasswordHash();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public User getByLogin(String login) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Criteria crit = session.createCriteria(User.class)
                    .add(Restrictions.eq("login", login));
            crit.setMaxResults(50);
            return (User) crit.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
