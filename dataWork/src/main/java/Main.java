import DAO.*;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println("Hello world");

        System.out.println("All cargos:");
        ((CargoDAOImpl)Factory.getInstance().getCargoDAO()).printAll();

        System.out.println("All Cities");
        ((CityDAOImpl)Factory.getInstance().getCityDAO()).printAll();

        System.out.println("All trucks");
        ((TruckDAOImpl)Factory.getInstance().getTruckDAO()).printAll();

        System.out.println("All drivers");
        ((DriverDAOImpl)Factory.getInstance().getDriverDAO()).printAll();

        System.out.println("Good bye!");
    }
}
