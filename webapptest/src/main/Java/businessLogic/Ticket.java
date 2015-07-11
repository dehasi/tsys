package businessLogic;

/**
 * Class represent just created baggage ticket
 * so, ticket has information about name and
 * weight of baggage
 * ans load and unload city ids
 */
public class Ticket {
    String name;
    int weight;
    int loadId;
    int unloadId;

    public Ticket() {
    }

    public Ticket(String name, int weight, int loadId, int unloadId) {
        this.name = name;
        this.weight = weight;
        this.loadId = loadId;
        this.unloadId = unloadId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLoadId() {
        return loadId;
    }

    public void setLoadId(int loadId) {
        this.loadId = loadId;
    }

    public int getUnloadId() {
        return unloadId;
    }

    public void setUnloadId(int unloadId) {
        this.unloadId = unloadId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", loadId=" + loadId +
                ", unloadId=" + unloadId +
                '}';
    }
}
