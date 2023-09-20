package app.ij.mlwithtensorflowlite;

public class FruitItem {
    private String name;
    private int number;
    private String time;

    public FruitItem(String name, int number, String time) {
        this.name = name;
        this.number = number;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getTime() {
        return time;
    }
}
