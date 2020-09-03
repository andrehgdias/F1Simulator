package sample;

import java.util.Arrays;
import java.util.Random;

public class GrandPrix {
    private Championship championship;
    private String city;
    private int laps;
    private float defaultLapTime;
    private float[] pilotTimes = new float[20];
    private String startWeather;
    private String weather;
    private int currentLap;
    private int[] positions = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 , 17 , 18 , 19 };

    public GrandPrix(Championship championship, String city, int laps, float defaultLapTime, String startWeather) {
        this.championship = championship;
        this.city = city;
        this.laps = laps;
        this.defaultLapTime = defaultLapTime;
        this.startWeather = startWeather;
        this.weather = startWeather;
        this.currentLap = 0;
        for (int i = 0; i < 20; i++) {
            this.pilotTimes[i] = 0;
        }
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public float getDefaultLapTime() {
        return defaultLapTime;
    }

    public void setDefaultLapTime(float defaultLapTime) {
        this.defaultLapTime = defaultLapTime;
    }

    public float[] getPilotTimes() {
        return pilotTimes;
    }

    public void setPilotTimes(float[] pilotTimes) {
        this.pilotTimes = pilotTimes;
    }

    public String getStartWeather() {
        return startWeather;
    }

    public void setStartWeather(String weather) {
        this.startWeather = weather;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getCurrentLap() {
        return currentLap;
    }

    public void setCurrentLap(int currentLap) {
        this.currentLap = currentLap;
    }

    public int[] getPositions() {
        return positions;
    }

    public void setPositions(int[] positions) {
        this.positions = positions;
    }

    public static int[] shuffleArray(int[] array) {
        Random random = new Random();
        int qtd = array.length;
        for (int i = 0; i < qtd; i++) {
            int position = random.nextInt(qtd);
            int swap = array[i];
            array[i] = array[position];
            array[position] = swap;
        }
        return array;
    }

    private void generateStarPosition() {
        float defaultTimeDifference = 2;
        shuffleArray(this.positions);
        for (int i = 0; i < 20; i++) {
            int pilotIdInPositionI = this.positions[i];
            this.pilotTimes[pilotIdInPositionI] = i * defaultTimeDifference;
        }
    }

    public float[] simulate() throws InterruptedException {
        Team[] teams = this.championship.getTeams();

        this.generateStarPosition();
        System.out.println(" > Start positions: " + Arrays.toString(this.positions));

        Thread[] threads = new Thread[20];
        for (int i = 1; i <= this.laps; i++) {
            int j = 0;
            this.currentLap = i;

            for (Team team : teams) {
                team.getPilots()[0].setCurrentGrandPrix(this);
                team.getPilots()[1].setCurrentGrandPrix(this);
                threads[j++] = new Thread(team.getPilots()[0]);
                threads[j++] = new Thread(team.getPilots()[1]);
            }

            for (int k = 0; k < 20; k++) {
                threads[k].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }
        }
        return this.pilotTimes;
    }
}
