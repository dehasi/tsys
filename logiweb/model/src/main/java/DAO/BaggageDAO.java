package DAO;

import model.Baggage;
import model.statuses.BaggageStatus;
import java.util.Set;

public interface BaggageDAO extends GeneticDAO<Baggage> {
    Set<Baggage> getBaggagesByStatus(BaggageStatus status);
    int getMaxId();
}
