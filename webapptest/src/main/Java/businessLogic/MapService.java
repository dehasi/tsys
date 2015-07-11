package businessLogic;

import DAO.MapDAOImpl;
import model.City;

/**
 * Created by Rafa on 09.07.2015.
 */
public class MapService {
    private MapDAOImpl mapDAO;

    public MapService(MapDAOImpl mapDAO) {
        this.mapDAO = mapDAO;
    }

    public int getDistance(City cityA, City cityB) {
        return getDistance(cityA.getId(), cityB.getId());
    }

    public int getDistance (int cityA, int cityB) {
        Integer dist = mapDAO.getDistance(cityA, cityB);

        if (dist == null) {
            dist = mapDAO.getDistance(cityB, cityA);
        }
        return dist;
    }

    public Integer getRoadLength(int[] road) {
        int length = 0;
        for(int i =1 ; i <  road.length; i++) {
            Integer dist = getDistance(road[i-1], road[i]);
            length += dist;
        }
        return length;
    }

}
