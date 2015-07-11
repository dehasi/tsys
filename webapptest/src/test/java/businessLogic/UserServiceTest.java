package businessLogic;

import DAO.UserDAOImpl;
import model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rafa on 30.06.2015.
 */
public class UserServiceTest {
    @Test
    public void testIsValidUser() throws ClassNotFoundException {
        UserService userService = new UserService(new UserDAOImpl((Class<User>) Class.forName("model.User")));
        Assert.assertEquals(userService.isValidUser("",""), false);
        Assert.assertEquals(userService.isValidUser("login","password"), true);
        Assert.assertEquals(userService.isValidUser("login","password2"), false);
        Assert.assertEquals(userService.isValidUser("LOGIN","password"), true);
    }


}
