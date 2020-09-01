package sample;

public class Mechanic extends Person {
    Pilot pilot;
    float timeChangingTyres;
    float timeRefueling;

    public Mechanic(int id, String name, float timeRefueling) {
        super(id, name);
        this.timeRefueling = timeRefueling;
    }

    public Mechanic(int id, String name, Pilot pilot, float timeChangingTyres, float timeRefueling) {
        super(id, name);
        this.pilot = pilot;
        this.timeChangingTyres = timeChangingTyres;
        this.timeRefueling = timeRefueling;
    }

    public void ChangeTyres() {

    }

    public void Refuel() {

    }
}
