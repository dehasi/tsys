package DAO;

import model.Baggage;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rafa on 25.06.2015.
 */
public class BaggageDAOImpl extends  GenericDAOImpl<Baggage> {

    public BaggageDAOImpl(Class<Baggage> clazz) {
        super(clazz);
    }

}
