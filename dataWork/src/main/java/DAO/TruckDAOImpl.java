package DAO;

import model.Truck;
import org.hibernate.Session;
import org.hibernate.jpa.internal.EntityManagerImpl;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Rafa on 20.06.2015.
 */
public class TruckDAOImpl extends GenericDAOImpl<Truck>{

    public TruckDAOImpl(Class<Truck> clazz) {
        super(clazz);
    }
    public Set<Truck> getTrucksForOrger() {
        Set<Truck> trucks = new HashSet<>();
        //intersetion
        return null;
    }

    //   	���� ��������� � ��������� ���������;
    public Set<Truck> getOKTrucks() {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
HibernateUtil.getSessionFactory().getCurrentSession().createCriteria("");

        return null;
    }

    //   	���� �� ��������� � ������ ������ ������� �������;
    public Set<Truck> getFreeTrucks() {
        return null;
    }

    //   	���� �������� �� �����������
    // (� ������ ��������/�������� ������ � ������� �� �������� ����������);
    public Set<Truck> getFitTrucks(int weight) {
        return null;
    }


}
