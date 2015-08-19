package model.statuses;

public enum DoneStatus {
    NOT_DONE(0),DONE(1);

    DoneStatus(long l) {
    }

    public static DoneStatus fromString(String status) {
        if (status.compareToIgnoreCase("done") == 0) {

            return DONE;

        } else if (status.compareToIgnoreCase("NOT_DONE") == 0) {

            return NOT_DONE;

        } else if (status.compareToIgnoreCase("NOTDONE") == 0) {

            return NOT_DONE;

        } else {

            return null;
        }
    }
}
