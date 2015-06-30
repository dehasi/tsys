package businessLogic;

import DAO.UserDAOImpl;

/**
 * Created by Rafa on 29.06.2015.
 */
public class UserLogic {
    protected UserDAOImpl userDAO = null;

    public UserLogic(UserDAOImpl userDAO) {
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

    public boolean isLoginExists(String login){
        return false;
    }
}
