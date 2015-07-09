package businessLogic;

import DAO.UserDAOImpl;
import model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rafa on 30.06.2015.
 */
public class UserLogicTest {
    @Test
    public void testIsValidUser() throws ClassNotFoundException {
        UserLogic userLogic = new UserLogic(new UserDAOImpl((Class<User>) Class.forName("model.User")));
        Assert.assertEquals(userLogic.isValidUser("",""), false);
        Assert.assertEquals(userLogic.isValidUser("login","password"), true);
        Assert.assertEquals(userLogic.isValidUser("login","password2"), false);
        Assert.assertEquals(userLogic.isValidUser("LOGIN","password"), true);
    }


}
