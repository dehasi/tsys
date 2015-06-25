package DAO;

import model.Truck;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rafa on 20.06.2015.
 */
public class TruckDAOImpl extends GenericDAOImpl<Truck>{

    public TruckDAOImpl(Class<Truck> clazz) {
        super(clazz);
    }

}
