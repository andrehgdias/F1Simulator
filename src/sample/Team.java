package sample;

public class Team {
    int id;
    String name;
    Car[] cars = new Car[2];
    Pilot[] pilots = new Pilot[2];

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team(int id, String name, Car[] cars, Pilot[] pilots) {
        this.id = id;
        this.name = name;
        this.cars = cars;
        this.pilots = pilots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car[] getCars() {
        return cars;
    }

    public void setCars(Car[] cars) {
        this.cars = cars;
    }

    public Pilot[] getPilots() {
        return pilots;
    }

    public void setPilots(Pilot[] pilots) {
        this.pilots = pilots;
    }
}
