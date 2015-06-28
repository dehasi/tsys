package DAO;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Rafa on 25.06.2015.
 */
public interface GeneticDAO <T> {
    void add(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    Collection getAll() throws SQLException;
    public T getById(Integer id) throws SQLException;
    public void printAll() throws SQLException ;
}
