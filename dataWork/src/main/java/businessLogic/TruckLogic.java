package businessLogic;

import DAO.TruckDAOImpl;
import model.Truck;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rafa on 28.06.2015.
 */
//o	�������� ������, ����������, �������������� � �������� ���
public class TruckLogic {

    protected TruckDAOImpl truckDAO = null;

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
    public Set<Truck> getTrucksForOrger(int weight) {
        Set<Truck> trucks = new HashSet<>();
        //intersetion
        return null;
    }
//   	���� ��������� � ��������� ���������;
    //SELECT * FROM truck WHERE status = 0
    public Set<Truck> getOKTrucks() {
        return null;
    }

//   	���� �� ��������� � ������ ������ ������� �������;
    //  //SELECT * FROM truck WHERE  id NOT IN (SELECT  DISTINCT truck FROM orderroure DISTINCT)
    public Set<Truck> getFreeTrucks() {
        return null;
    }

    //   	���� �������� �� �����������
    // (� ������ ��������/�������� ������ � ������� �� �������� ����������);
    //SELECT * FROM truck WHERE  capacity >= weight
    public Set<Truck> getFitTrucks(int weight) {
        return null;
    }
}
