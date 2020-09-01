package sample;

import javax.print.attribute.standard.MediaSize;

public class Pilot extends Person{

    Car car;
    Mechanic[] mechanics = new Mechanic[4];
    Engineer engineer;

    public Pilot(int id, String name) {
        super(id, name);
    }

    public Pilot(int id, String name, Car car, Mechanic[] mechanics, Engineer engineer) {
        super(id, name);
        this.car = car;
        this.mechanics = mechanics;
        this.engineer = engineer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Mechanic[] getMechanics() {
        return mechanics;
    }

    public void setMechanics(Mechanic[] mechanics) {
        this.mechanics = mechanics;
    }

    public Engineer getEngineer() {
        return engineer;
    }

    public void setEngineer(Engineer engineer) {
        this.engineer = engineer;
    }
}
