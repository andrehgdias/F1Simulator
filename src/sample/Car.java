package sample;

public class Car {
    int id;
    Team team;
    Pilot pilot;
    int fuel;
    String status;
    String tyres;

    public Car() {
    }

    public Car(int id, Team team, Pilot pilot, int fuel, String status, String tyres) {
        this.id = id;
        this.team = team;
        this.pilot = pilot;
        this.fuel = fuel;
        this.status = status;
        this.tyres = tyres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTyres() {
        return tyres;
    }

    public void setTyres(String tyres) {
        this.tyres = tyres;
    }
}
