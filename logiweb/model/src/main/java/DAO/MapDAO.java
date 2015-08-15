package DAO;

import model.Map;

public interface MapDAO extends GeneticDAO<Map> {
    Integer getDistance(int a, int b);
}
