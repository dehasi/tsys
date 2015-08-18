package businessLogic;

import DAO.UserDAOImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserDAOImpl mockUserDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testIsValidUser() throws ClassNotFoundException {
        UserService userService = new UserService(mockUserDAO);
        Mockito.when(mockUserDAO.getByLogin("12")).thenReturn(null);
        Mockito.when(mockUserDAO.getUserPasswordHash("")).thenReturn(42L);
        Assert.assertEquals(userService.isValidUser("", ""), false);
        Assert.assertEquals(userService.isValidUser("12", "12"), false);
        Mockito.verify(mockUserDAO, Mockito.atLeastOnce()).getUserPasswordHash("12");
    }

    @Test
    public void getUserStatusTest() {
        UserService userService = new UserService(mockUserDAO);
        Mockito.when(mockUserDAO.getByLogin("")).thenReturn(null);
        userService.getUserStatus("");
        Mockito.verify(mockUserDAO, Mockito.atLeastOnce()).getByLogin("");
    }

}
