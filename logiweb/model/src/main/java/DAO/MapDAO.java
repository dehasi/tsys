package DAO;

import model.Map;

/**
 * Created by Rafa on 14.08.2015.
 */
public interface MapDAO extends GeneticDAO<Map> {
    Integer getDistance(int a, int b);
}
