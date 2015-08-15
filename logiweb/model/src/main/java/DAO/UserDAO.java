package DAO;

import model.User;

public interface UserDAO extends GeneticDAO<User> {
    Long getUserPasswordHash(String login);

    User getByLogin(String login);
}
