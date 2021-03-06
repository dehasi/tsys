package businessLogic;

import DAO.TruckDAO;
import model.City;
import model.Truck;
import model.statuses.TruckStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represent work with trucks
 * add, edit, delete, search and refactor tucks
 *
 */
@Service
@Transactional
public class TruckService {
    private static Logger logger = Logger.getLogger(TruckService.class);
    @Autowired
    private TruckDAO truckDAO;
    @Autowired
    OrderService orderService;

    public TruckService() {
    }

    public TruckDAO getTruckDAO() {
        return truckDAO;
    }

    public void setTruckDAO(TruckDAO truckDAO) {
        this.truckDAO = truckDAO;
    }

    /**
     * Construct TruckService class.
     * @param truckDAO Truck DAO class
     */
    public TruckService(TruckDAO truckDAO) {
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

    public Set<Truck> getOKTrucks() {
        return addOrderForTrucks(truckDAO.getTrucksByStatus(TruckStatus.OK));
    }

    /**
     * Find truck that don't do any orders
     * @return set of trucks
     * @throws SQLException
     */
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

    /**
     * Find truck that ready to do order
     * @param weight weight of baggage
     * @param city start city in order
     * @return set of trucks ready for do order
     */
    public Set<Truck> getFitTrucks(int weight, City city) {
        Set<Truck> trucks = addOrderForTrucks(truckDAO.getFitTrucks(weight, city));
        Set<Truck> forOrder = new HashSet<>();
        for(Truck t : trucks) {
            if (t.getOrderId() == null) {
                forOrder.add(t);
            }
        }
        return forOrder;
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

    /**
     * connect truck and its order
     * @param truck truck
     * @return truck with aplied order
     */
    private Truck addOrderForTruck(Truck truck) {
        if (truck == null) {
            return truck;
        }

        truck.setOrderId(orderService.getOrderIdByTruck(truck));

        return truck;
    }

    /**
     * connetcts truck and its order
     * @param trucks list of trucks
     * @return set of trucks with applied order id
     */
    private Set<Truck>  addOrderForTrucks(Set<Truck> trucks) {
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
