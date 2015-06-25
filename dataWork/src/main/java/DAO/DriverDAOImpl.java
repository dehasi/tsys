package DAO;

import model.Driver;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rafa on 21.06.2015.
 */
public class DriverDAOImpl extends GenericDAOImpl<Driver> {


    public DriverDAOImpl(Class<Driver> clazz) {
        super(clazz);
    }
}
