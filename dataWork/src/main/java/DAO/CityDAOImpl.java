package DAO;

import model.City;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by Rafa on 20.06.2015.
 */
public class CityDAOImpl extends GenericDAOImpl<City> {

    public CityDAOImpl(Class<City> clazz) {
        super(clazz);
    }

}
