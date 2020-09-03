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

    public void OrderChangeTyres(String type) {
        System.out.println(" > " + this.pilot.getName() + " is changing tyres...");
        Mechanic mechanic = this.pilot.getMechanics()[(int) (Math.random() * 4)];
        float eventTime = mechanic.getTimeChangingTyres();
        this.pilot.getCar().setTyres(type);
        this.pilot.setElapsedTimeOfEvents(this.pilot.getElapsedTimeOfEvents() + eventTime);
        System.out.println(" > " + mechanic.getName() + " spent " + eventTime + "s changing tyres");
    }

    public void OrderRefueling() {
        System.out.println(" > " + this.pilot.getName() + " is refueling...");
        Mechanic mechanic = this.pilot.getMechanics()[(int) (Math.random() * 4)];
        float eventTime = mechanic.getTimeRefueling();
        this.pilot.setElapsedTimeOfEvents(this.pilot.getElapsedTimeOfEvents() + eventTime);
        System.out.println(" > " + mechanic.getName() + " spent " + eventTime + "s refueling the car");
    }


    public void InformPenality() {
        System.out.println(" > " + this.pilot.getName() + " suffered a penality...");
        float eventTime = DashboardController.getChampionship().getPenaltyTime();
        this.pilot.setElapsedTimeOfEvents(this.pilot.getElapsedTimeOfEvents() + eventTime);
    }
}
