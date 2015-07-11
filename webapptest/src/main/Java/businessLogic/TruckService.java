package businessLogic;

import DAO.TruckDAOImpl;
import model.City;
import model.Truck;
import model.statuses.TruckStatus;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represent work with trucks
 * add, edit, delete, search and refactor tucks
 *
 */
public class TruckService {

    private TruckDAOImpl truckDAO = null;

    /**
     * Construct TruckService class.
     * @param truckDAO Truck DAO class
     */
    public TruckService(TruckDAOImpl truckDAO) {
        this.truckDAO = truckDAO;
    }

    /**
     *
     * @return all existing trucks
     * @throws SQLException
     */
    public Set<Truck> getAllTrucks() throws SQLException {
        return addOrderForTrucks(new HashSet<>(truckDAO.getAll()));
    }

    /**
     * adds new truck in database
     * @param truck element to be added to database
     * @throws SQLException
     */
    public void addTruck(Truck truck) throws SQLException {
        truckDAO.add(truck);
    }

    /**
     * deletes truck from database
     * @param truck element to be deleted to database
     * @throws SQLException
     */
    public void deleteTruck(Truck truck) throws SQLException {
        truckDAO.delete(truck);
    }

    /**
     *
     * @param truck element to be update to database
     * @throws SQLException
     */
    public void updateTruck(Truck truck) throws SQLException {
        truckDAO.update(truck);
    }

    //SELECT * FROM truck WHERE
    //status = 0 AND
    //id NOT IN (SELECT  DISTINCT truck FROM orderroure) AND
    //capacity >= weight
    public Set<Truck> getTrucksForOrder(int weight) {
       return addOrderForTrucks(truckDAO.getTrucksForOrder(weight));
    }

    public Set<Truck> getOKTrucks() {
        return addOrderForTrucks(truckDAO.getTrucksByStatus(TruckStatus.OK));
    }

//   	фура не выполняет в данный момент никаких заказов;
    //  //SELECT * FROM truck WHERE  id NOT IN (SELECT  DISTINCT truck FROM orderroure DISTINCT)
    public Set<Truck> getFreeTrucks() throws SQLException {
        Set<Truck> trucks = getAllTrucks();
        if (trucks == null) {
            return trucks;
        }
        Set<Truck> inFreeTrucks = new HashSet<>();
        for(Truck truck : trucks) {
            if (truck.getOrderId() == null)
                inFreeTrucks.add(addOrderForTruck(truck));
        }
        return inFreeTrucks;
    }

    //   	фура подходит по вместимости
    // (с учетом погрузки/выгрузки грузов в городах по маршруту следования);
    //SELECT * FROM truck WHERE  capacity >= weight
    public Set<Truck> getFitTrucks(int weight, City city) {

        return addOrderForTrucks(truckDAO.getFitTrucks(weight, city));
    }

    public Set<Truck> getDefectiveTrucks() {

        return truckDAO.getTrucksByStatus(TruckStatus.DEFECTIVE);
    }


    public Set<Truck> getInOrderTrucks() throws SQLException {
        Set<Truck> trucks = getAllTrucks();
        if (trucks == null) {
            return trucks;
        }
        Set<Truck> inOrderTrucks = new HashSet<>();
        for(Truck truck : trucks) {
            if (truck.getOrderId() != null)
            inOrderTrucks.add(addOrderForTruck(truck));
        }
        return inOrderTrucks;
    }

    public Truck getTruckById(String id) {
        return addOrderForTruck(truckDAO.getTruckById(id));
    }

    private Truck addOrderForTruck(Truck truck){
        if (truck == null) {
            return truck;
        }

        truck.setOrderId(BusinessFactory.getInstance().getOrderLogic().getOrderIdByTruck(truck.getId()));

        return truck;
    }

    private Set<Truck>  addOrderForTrucks(Set<Truck> trucks){
        if (trucks == null) {
            return trucks;
        }
        Set<Truck> t = new HashSet<>();
        for(Truck truck : trucks) {
            t.add(addOrderForTruck(truck));
        }
        return t;
    }
}
