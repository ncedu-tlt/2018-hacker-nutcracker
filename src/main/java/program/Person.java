package program;

public class Person {
    public int id;
    public String name;
    public String way;
    public int USD;

    public Person(int id, String name, String way, int USD) {
        this.id = id;
        this.name = name;
        this.way = way;
        this.USD = USD;
    }

    @Override
    public String toString() {
        return id + "    " + name + "  " + way + "  " + USD;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public void setUSD(int USD) {
        this.USD = USD;
    }

    public String getName() {
        return name;
    }

    public String getWay() {
        return way;
    }

    public int getUSD() {
        return USD;
    }
}