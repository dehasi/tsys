package classes.data.statuses;

/**
 * Created by Rafa on 20.06.2015.
 */
public enum BaggageStatus {
    PRODUCED(0), SHIPPED(1), DONE(2);

    BaggageStatus(long l) {

    }

    public static BaggageStatus fromString(String status) {

        if (status.compareToIgnoreCase("PRODUCED") == 0) {

            return PRODUCED;

        } else if (status.compareToIgnoreCase("SHIPPED") == 0) {

            return SHIPPED;

        } else if (status.compareToIgnoreCase("DONE") == 0) {

            return DONE;

        } else {

            return PRODUCED;
        }
    }
}
