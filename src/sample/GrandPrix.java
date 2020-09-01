package sample;

public class GrandPrix {
    Championship championship;
    String city;
    int laps;
    float defaultLapTime;
    float[] pilotTimes = new float[20];
    String weather;

    public GrandPrix(Championship championship, String city, int laps, float defaultLapTime, String weather) {
        this.championship = championship;
        this.city = city;
        this.laps = laps;
        this.defaultLapTime = defaultLapTime;
        this.weather = weather;
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

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void simulate() {

    }
}
