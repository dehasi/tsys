package businessLogic;

import DAO.TruckDAOImpl;
import model.Truck;
import model.statuses.TruckStatus;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rafa on 28.06.2015.
 */
//o	просмотр списка, добавление, редактирование и удаление фур
public class TruckLogic {

    private TruckDAOImpl truckDAO = null;

    public TruckLogic(TruckDAOImpl truckDAO) {
        this.truckDAO = truckDAO;
    }

    public Set<Truck> getAllTrucks() throws SQLException {
        return addOrderForTrucks(new HashSet<Truck>(truckDAO.getAll()));
    }

    public void addTruck(Truck truck) throws SQLException {
        truckDAO.add(truck);
    }

    public void deleteTruck(Truck truck) throws SQLException {
        truckDAO.delete(truck);
    }

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
    public Set<Truck> getFitTrucks(int weight) {

        return addOrderForTrucks(truckDAO.getFitTrucks(weight));
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
        try {
            truck.setOrderId(BusinessFactory.getInstance().getOrderLogic().getOrderIdByTruck(truck.getId()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            truck.setOrderId(null);
        }
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
