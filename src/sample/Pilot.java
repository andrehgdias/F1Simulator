package sample;

import java.lang.management.GarbageCollectorMXBean;

public class Pilot extends Person implements Runnable {

    private Team team;
    private Car car;
    private Mechanic[] mechanics = new Mechanic[4];
    private Engineer engineer;
    private float elapsedTimeOfEvents;
    private float raceTime;
    private GrandPrix currentGrandPrix;

    public Pilot(){
        this.elapsedTimeOfEvents = 0;
        this.raceTime = 0;
    }

    public Pilot(int id, String name, Car car, Team team) {
        super(id, name);
        this.car = car;
        this.team = team;
        this.elapsedTimeOfEvents = 0;
        this.raceTime = 0;
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

    public float getElapsedTimeOfEvents() {
        return elapsedTimeOfEvents;
    }

    public void setElapsedTimeOfEvents(float elapsedTimeOfEvents) {
        this.elapsedTimeOfEvents = elapsedTimeOfEvents;
    }

    public float getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(float raceTime) {
        this.raceTime = raceTime;
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public GrandPrix getCurrentGrandPrix() {
        return currentGrandPrix;
    }

    public void setCurrentGrandPrix(GrandPrix currentGrandPrix) {
        this.currentGrandPrix = currentGrandPrix;
        this.raceTime = 0;
        this.getCar().setFuel(Car.MAX_FUEL);
    }

    @Override
    public void run() {
        System.out.println(" > " + this.getName() + " is running at lap " + this.currentGrandPrix.getCurrentLap());
        this.elapsedTimeOfEvents = 0;

        if(Math.random()*100 > 85)Event.penalty(this); // 85% de chance do piloto sofrer uma penalidade

        if (this.currentGrandPrix.getCurrentLap() == 11) Event.refueling(this);
        if ( this.currentGrandPrix.getCurrentLap()/Car.MAX_FUEL > 1 && this.currentGrandPrix.getCurrentLap()%Car.MAX_FUEL == 0) Event.refueling(this);

        this.raceTime += this.currentGrandPrix.getDefaultLapTime();

        float[] pilotTimes = this.currentGrandPrix.getPilotTimes();
        pilotTimes[this.getId()] = pilotTimes[this.getId()] + this.elapsedTimeOfEvents + this.raceTime;
        this.currentGrandPrix.setPilotTimes(pilotTimes);
    }
}
