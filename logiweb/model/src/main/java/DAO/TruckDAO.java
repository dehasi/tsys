package DAO;

import model.City;
import model.Truck;
import model.statuses.TruckStatus;

import java.util.Set;

public interface TruckDAO extends GeneticDAO<Truck> {
    Set<Truck> getTrucksForOrder(int weight);

    Truck getTruckById(String id);

    Set<Truck> getTrucksByStatus(TruckStatus status);

    Set<Truck> getFitTrucks(int weight, City city);
}
