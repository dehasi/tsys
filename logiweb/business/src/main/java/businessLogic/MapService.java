package businessLogic;

import DAO.MapDAO;
import model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class represent business logic for map
 *
 */
@Service
public class MapService {
    @Autowired
    private MapDAO mapDAO;

    public MapService(MapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }

    public MapService() {
    }

    public MapDAO getMapDAO() {
        return mapDAO;
    }

    public void setMapDAO(MapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }

    public int getDistance(City cityA, City cityB) {

        return getDistance(cityA.getId(), cityB.getId());
    }

    public int getDistance (int cityA, int cityB) {
        if (cityA == cityB) {
            return 0;
        }
        Integer dist = mapDAO.getDistance(cityA, cityB);

        if (dist == null) {
            dist = mapDAO.getDistance(cityB, cityA);
        }
        return dist;
    }

    /**
     * Function counts length of all road for order
     * @param road array of city ids in order driver drive
     * @return sum of distance
     */
    public Integer getRoadLength(int[] road) {
        int length = 0;
        for(int i =1 ; i <  road.length; i++) {
            Integer dist = getDistance(road[i-1], road[i]);
            length += dist;
        }
        return length;
    }

}
