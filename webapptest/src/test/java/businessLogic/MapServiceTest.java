package businessLogic;

import DAO.MapDAOImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by Rafa on 11.07.2015.
 */
public class MapServiceTest {

    @Mock
    private MapDAOImpl mockMapDAOImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getRoadLengthTest() {
        MapService mapService = new MapService(mockMapDAOImpl);
        Mockito.when(mockMapDAOImpl.getDistance(1,2)).thenReturn(1);
        Mockito.when(mockMapDAOImpl.getDistance(2,3)).thenReturn(2);
        Mockito.when(mockMapDAOImpl.getDistance(3,1)).thenReturn(1);
        int[] road =  {1,2,3,1};
        int r = mapService.getRoadLength(road);
        Assert.assertEquals(r, 4);
    }
}
