package sample;

public class Engineer extends Person {
    Pilot pilot;

    public Engineer(int id, String name) {
        super(id, name);
    }

    public Engineer(int id, String name, Pilot pilot) {
        super(id, name);
        this.pilot = pilot;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public void OrderChangeTyres() {

    }

    public void OrderRefueling() {

    }

    public void OrderChangePosition() {

    }
}
