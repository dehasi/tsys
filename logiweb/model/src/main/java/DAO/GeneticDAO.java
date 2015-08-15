package DAO;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Generic DAO interface
 * represent simple CRUD operations
 */
public interface GeneticDAO <T> {
    void add(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    Collection getAll() throws SQLException;
    T getById(Integer id) throws SQLException;
    void printAll() throws SQLException ;
}
