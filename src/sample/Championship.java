package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Championship {
    private String name;
    private Team[] teams = new Team[10];
    private ArrayList<GrandPrix> grandPrixs;
    private int numOfRaces;


    ArrayList<float[]> accumulatedTimeByRace = new ArrayList<float[]>();
    float[] accumulatedTime = new float[20];

    float penaltyTime;
    private int rainProbability;

    public Championship(String name, float penaltyTime, int rainProbability) {
        this.name = name;
        this.penaltyTime = penaltyTime;
        this.rainProbability = rainProbability;
        for (int i = 0; i < 20; i++) {
            this.accumulatedTime[i] = 0;
        }
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

    public ArrayList<float[]> getAccumulatedTimeByRace() {
        return accumulatedTimeByRace;
    }

    public void setAccumulatedTimeByRace(ArrayList<float[]> accumulatedTimeByRace) {
        this.accumulatedTimeByRace = accumulatedTimeByRace;
    }

    public float[] getAccumulatedTime() {
        return accumulatedTime;
    }

    public void setAccumulatedTime(float[] accumulatedTime) {
        this.accumulatedTime = accumulatedTime;
    }

    public float getPenaltyTime() {
        return penaltyTime;
    }

    public void setPenaltyTime(float penaltyTime) {
        this.penaltyTime = penaltyTime;
    }

    public int getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(int rainProbability) {
        this.rainProbability = rainProbability;
    }

    public void start(int i) throws InterruptedException, IOException {
        Stage stageRunning = new Stage();
        this.showRunningScene(stageRunning);

        boolean rainEvent = !(Math.random() * 100 > this.rainProbability);
        System.out.println("==================================================");
        System.out.println(" > Rain Event: " + rainEvent);
        if(rainEvent) Event.rain(this.getTeams());

        System.out.println("==================================================");
        System.out.println(" > The " + this.getGrandPrixs().get(i).getCity() + " Grand Prix ");

        float[] lapTimesByPilot = this.grandPrixs.get(i).simulate();
        System.out.println(" > Lap times (index represents pilot id): " + Arrays.toString(lapTimesByPilot));

        this.accumulatedTimeByRace.add(lapTimesByPilot);

        this.generateScoreboard(i);

        for (int j = 0; j < 20; j++) {
            this.accumulatedTime[j] = this.accumulatedTime[j] + lapTimesByPilot[j];
        }
        stageRunning.close();
    }

    private void showRunningScene(Stage stageRunning) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RunningSimulation.fxml"));
        Parent root = loader.load();
        RunningSimulationController controller = loader.getController();
        stageRunning.setTitle("Rodando Simulação");
        stageRunning.show();
        Scene sceneRunning= new Scene(root);
        stageRunning.setScene(sceneRunning);
        stageRunning.setResizable(false);
        controller.setStage(stageRunning);
    }

    public void generateScoreboard(int indexGrandPrix) throws IOException {
        Stage stageRunning = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GrandPrixScoreboard.fxml"));
        Parent root = loader.load();
        GrandPrixScoreboardController controller = loader.getController();
        stageRunning.setTitle("Grande Prêmio de " + this.grandPrixs.get(indexGrandPrix).getCity());
        stageRunning.show();
        Scene sceneRunning= new Scene(root);
        stageRunning.setScene(sceneRunning);
        stageRunning.setResizable(false);
        controller.setStage(stageRunning);
        controller.setGrandPrix(DashboardController.getChampionship().getGrandPrixs().get(indexGrandPrix));
        controller.init();
    }
}
