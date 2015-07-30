package model.statuses;

/**
 * Created by Rafa on 20.06.2015.
 */
public enum DriverStatus {
    REST(0), WORK(1), DRIVING(2);

    DriverStatus(long l) {

    }

    public  static DriverStatus fromString(String status) {

        if (status.compareToIgnoreCase("rest") == 0) {
            return REST;
        } else if (status.compareToIgnoreCase("work") == 0) {
            return WORK;
        } else if (status.compareToIgnoreCase("driving") == 0) {
            return DRIVING;
        } else {
            return REST;
        }

    }
}
