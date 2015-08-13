package DAO;

import model.City;

/**
 * Created by Rafa on 14.08.2015.
 */
public interface CityDAO extends GeneticDAO<City> {
    City getCityByName(String name);
}
