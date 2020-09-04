package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ChampionshipScoreboardController {
    public Stage stage;

    public Label labelChampionshipName;
    public VBox vBoxResults;
    public VBox vBoxPodium;
    public VBox vBoxPodiumPilots;
    public VBox vBoxPodiumTeams;
    private GrandPrix grandPrix;

    public GrandPrix getGrandPrix() {
        return grandPrix;
    }

    public void setGrandPrix(GrandPrix grandPrix) {
        this.grandPrix = grandPrix;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void init() {
        this.labelChampionshipName.setText(DashboardController.getChampionship().getName());
        this.generateScoreboard();
    }

    private void generateScoreboard() {
        Pilot[] pilots = new Pilot[20];
        Team[] teams = DashboardController.getChampionship().getTeams();
        float[] temp = DashboardController.getChampionship().getAccumulatedTime().clone();
        float[] teamTimes = new float[10];
        int j = 0;
        int k = 0;
        for (int i = 0; i < 10; i++) {
            pilots[k++] = teams[i].getPilots()[0];
            pilots[k++] = teams[i].getPilots()[1];
            teamTimes[i] = temp[j] + temp[j+1];
            j = j + 2;
        }
        float[] arrayAux = teamTimes.clone();
        Arrays.sort(teamTimes);

        int podiumTeam = 0;

        for (int i = 0; i < 10; i++) {

            int teamId = GrandPrixScoreboardController.indexOf(arrayAux, teamTimes[i]);

            System.out.println(" > Team Id: " + teamId);

            Label labelTeam = new Label();
            labelTeam.setText((i+1) + ". " + teams[teamId].getName() + " \t - \t Tempo acumulado: " + teamTimes[i]);
            labelTeam.setFont(new Font("Arial", 12));
            this.vBoxResults.getChildren().addAll(labelTeam);

            if (podiumTeam < 3) {
                Label labelTeamBigger = new Label();
                labelTeamBigger.setText((i+1) + ". " + teams[teamId].getName());
                labelTeamBigger.setFont(new Font("Arial", 14));
                this.vBoxPodiumTeams.getChildren().addAll(labelTeamBigger);
                podiumTeam++;
            }
        }

        ArrayList<float[]> tempPilotTimes = DashboardController.getChampionship().getAccumulatedTimeByRace();
        float[] pilotTimes = new float[20];

        int max = tempPilotTimes.size();

        for (int i = 0; i < 20; i++) {
            for (float[] tempPilotTime : tempPilotTimes) {
                pilotTimes[i] = pilotTimes[i] + tempPilotTime[i];
            }
        }

        System.out.println(" > Pilot times: " + Arrays.toString(pilotTimes));

        float[] arrayAuxPilots= pilotTimes.clone();
        Arrays.sort(pilotTimes);

        for (int i = 0; i < 3; i++) {
            int pilotId = GrandPrixScoreboardController.indexOf(arrayAuxPilots, pilotTimes[i]);

            System.out.println(" > Pilot Id: " + pilotId);

            Label labelPilot = new Label();
            labelPilot.setText((i+1) + ". " + pilots[pilotId].getName() + " \t - \t Tempo acumulado: " + pilotTimes[i] + "s");
            labelPilot.setFont(new Font("Arial", 14));
            this.vBoxPodiumPilots.getChildren().addAll(labelPilot);
        }
    }
}
