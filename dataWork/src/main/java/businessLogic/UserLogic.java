package businessLogic;

import DAO.UserDAOImpl;

/**
 * Created by Rafa on 29.06.2015.
 */
public class UserLogic {
    protected UserDAOImpl truckDAO = null;

    public UserLogic(UserDAOImpl truckDAO) {
        this.truckDAO = truckDAO;
    }

    boolean isValidUser(String login, String password){

        return false;

    }
}
