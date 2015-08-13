package DAO;

import model.City;
import model.Truck;
import model.statuses.TruckStatus;

import java.util.Set;

/**
 * Created by Rafa on 13.08.2015.
 */
public interface TruckDAO extends GeneticDAO<Truck> {
    Set<Truck> getTrucksForOrder(int weight);

    Truck getTruckById(String id);

    Set<Truck> getTrucksByStatus(TruckStatus status);

    Set<Truck> getFitTrucks(int weight, City city);
}
