package DAO;

import model.User;

/**
 * Created by Rafa on 14.08.2015.
 */
public interface UserDAO extends GeneticDAO<User> {
    Long getUserPasswordHash(String login);

    User getByLogin(String login);
}
