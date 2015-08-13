package businessLogic;

import DAO.UserDAO;
import model.User;
import model.statuses.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Class for user business logic
 * helps user login do
 */
@Service
public class UserService {
    @Autowired
    protected UserDAO userDAO;

    public UserService() {
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean isValidUser(String login, String password){
        if(login.length() == 0) return  false;
        if(password.length() == 0) return  false;
        int pass =  password.hashCode();
        Long pass2 =userDAO.getUserPasswordHash(login);
        if (pass2 == null) return false;
        return pass == pass2;
    }

    public UserStatus getUserStatus(String login) {
        User user = userDAO.getByLogin(login);
        if (user != null) {
            return user.getStatus();
        }
        else {
            return null;
        }
    }

    public void addUser(User u) throws SQLException {
        userDAO.add(u);
    }
}
