import DAO.*;
import businessLogic.BusinessFactory;
import businessLogic.DriverLogic;
import businessLogic.Ticket;
import businessLogic.TruckService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import model.*;
import org.apache.log4j.Logger;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


class Main {
    private static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Hello world");


        System.out.println("Good bye!");

    }

}
