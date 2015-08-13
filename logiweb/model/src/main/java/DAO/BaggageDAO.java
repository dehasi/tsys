package DAO;

import model.Baggage;
import model.statuses.BaggageStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Rafa on 13.08.2015.
 */

public interface BaggageDAO extends GeneticDAO<Baggage> {
    Set<Baggage> getBaggagesByStatus(BaggageStatus status);

    int getMaxId();
}
