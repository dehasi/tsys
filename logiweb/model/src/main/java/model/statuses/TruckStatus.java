package model.statuses;

/**
 * Created by Rafa on 20.06.2015.
 */
public enum TruckStatus {
    OK(0), DEFECTIVE(1);

    TruckStatus(long l) {
    }

    public static TruckStatus fromInt(int x) {
        switch(x) {
            case 0:
                return TruckStatus.OK;
            case 1:
                return TruckStatus.DEFECTIVE;
            default:
                return TruckStatus.DEFECTIVE;
        }
    }
}
