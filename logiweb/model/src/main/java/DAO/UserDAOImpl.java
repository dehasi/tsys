package DAO;

import model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
@Transactional(propagation= Propagation.REQUIRED)
public class UserDAOImpl extends  GenericDAOImpl<User> implements UserDAO {
    Logger logger = Logger.getLogger(UserDAOImpl.class);
    public UserDAOImpl(Class<User> clazz) {
        super(clazz);
    }
    public UserDAOImpl() {
        super();
    }

    @Override
    public Long getUserPasswordHash(String login) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot =criteriaQuery.from(User.class);
            List<User> u = getEntityManager().createQuery(criteriaQuery.select(userRoot)
                    .where(criteriaBuilder.equal(userRoot.get("login"), login)))
                    .getResultList();
            User user = u.get(0);
            if (user == null) return 0L;
            return user.getPasswordHash();

        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public User getByLogin(String login) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot =criteriaQuery.from(User.class);
            List<User> u = getEntityManager().createQuery(criteriaQuery.select(userRoot)
                    .where(criteriaBuilder.equal(userRoot.get("login"), login)))
                    .getResultList();
            return u.get(0);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}
