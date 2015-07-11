package businessLogic;

import DAO.UserDAOImpl;
import model.User;
import model.statuses.UserStatus;

import javax.jws.soap.SOAPBinding;

/**
 * Class for user business logic
 * helps user login do
 */
public class UserService {
    protected UserDAOImpl userDAO = null;

    public UserService(UserDAOImpl userDAO) {
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
}
