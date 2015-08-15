package DAO;

import model.City;

public interface CityDAO extends GeneticDAO<City> {
    City getCityByName(String name);
}
