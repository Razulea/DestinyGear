import java.util.ArrayList;
import java.util.List;

public class Gear {

    private int type;
    private List<Integer> stats = new ArrayList<>();
    private boolean exotic;
    private final int id;
    private String name;

    public Gear(int type, List<Integer> stats, boolean exotic, int id, String name) {
        this.id = id;
        this.type = type;
        this.stats = stats;
        this.exotic = exotic;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getStats() {
        return stats;
    }

    public void setStats(List<Integer> stats) {
        this.stats = stats;
    }

    public boolean isExotic() {
        return exotic;
    }

    public void setExotic(boolean exotic) {
        this.exotic = exotic;
    }
}
