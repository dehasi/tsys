import com.google.gson.Gson;
import model.Driver;
import org.apache.log4j.Logger;

import java.sql.SQLException;


class Main {
    private static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Hello world");

        Driver d = new Driver();
        d.setId(42);
        d.setName("Vasa");
        d.setLastName("Pupkin");

        Gson gson = new Gson();

        String s = gson.toJson(d);

        System.out.println("Good bye!");

    }

}
