package sample;

import java.util.ArrayList;

public class Championship {
    String name;
    Team[] teams = new Team[10];
    ArrayList<GrandPrix> grandPrixs;
    int numOfRaces;
    float[][] accumulatedTime;
    float penaltyTime;

    public Championship(String name, float penaltyTime) {
        this.name = name;
        this.penaltyTime = penaltyTime;
    }

    public Championship(String name, Team[] teams, ArrayList<GrandPrix> grandPrixs, int numOfRaces, float[][] accumulatedTime, float penaltyTime) {
        this.name = name;
        this.teams = teams;
        this.grandPrixs = grandPrixs;
        this.numOfRaces = numOfRaces;
        this.accumulatedTime = accumulatedTime;
        this.penaltyTime = penaltyTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team[] getTeams() {
        return teams;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }

    public ArrayList<GrandPrix> getGrandPrixs() {
        return grandPrixs;
    }

    public void setGrandPrixs(ArrayList<GrandPrix> grandPrixs) {
        this.grandPrixs = grandPrixs;
    }

    public int getNumOfRaces() {
        return numOfRaces;
    }

    public void setNumOfRaces(int numOfRaces) {
        this.numOfRaces = numOfRaces;
    }

    public float[][] getAccumulatedTime() {
        return accumulatedTime;
    }

    public void setAccumulatedTime(float[][] accumulatedTime) {
        this.accumulatedTime = accumulatedTime;
    }

    public float getPenaltyTime() {
        return penaltyTime;
    }

    public void setPenaltyTime(float penaltyTime) {
        this.penaltyTime = penaltyTime;
    }

    public void start() {

    }

    public void generateScoreboard() {

    }
}
