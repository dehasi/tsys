package businessLogic;

import DAO.TruckDAOImpl;
import model.Truck;

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
        return new HashSet<Truck>(truckDAO.getAll());
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
       return truckDAO.getTrucksForOrder(weight);
    }

    public Set<Truck> getOKTrucks() {
        return truckDAO.getOKTrucks();
    }

//   	фура не выполняет в данный момент никаких заказов;
    //  //SELECT * FROM truck WHERE  id NOT IN (SELECT  DISTINCT truck FROM orderroure DISTINCT)
    public Set<Truck> getFreeTrucks() {
        return truckDAO.getFreeTrucks();
    }

    //   	фура подходит по вместимости
    // (с учетом погрузки/выгрузки грузов в городах по маршруту следования);
    //SELECT * FROM truck WHERE  capacity >= weight
    public Set<Truck> getFitTrucks(int weight) {
        return truckDAO.getFitTrucks(weight);
    }


}
