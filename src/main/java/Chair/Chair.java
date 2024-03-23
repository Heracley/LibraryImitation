package Chair;

public class Chair {
    private Integer number;
    private String name;

    public Chair(Integer number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getName() {
        return name + " (№ " + number + ")";
    }
}
