package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class GrandPrixScoreboardController {
    public Stage stage;

    public Label labelGrandPrixName;
    public Label labelLaps;
    public Label labelDefaultLapTime;
    public Label labelStartWeather;
    public VBox vBoxResults;
    public Label labelFinalWeather;
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
        this.labelGrandPrixName.setText("Grande Prêmio de " + this.grandPrix.getCity());
        this.labelLaps.setText("Nº de voltas: " + this.grandPrix.getLaps());
        this.labelDefaultLapTime.setText("Tempo mínimo p/volta: : " + this.grandPrix.getDefaultLapTime());
        this.labelStartWeather.setText("Clima inicial: " + this.grandPrix.getStartWeather());
        this.labelFinalWeather.setText("Clima final: " + this.grandPrix.getWeather());
        this.createTable();
    }

    private void createTable() {
        Team[] teams = DashboardController.getChampionship().getTeams();
        float[] pilotTimes = this.grandPrix.getPilotTimes().clone();
        Arrays.sort(pilotTimes);

        for (int i = 0; i < 20; i++) {
            int pilotId = indexOf(this.grandPrix.getPilotTimes(), pilotTimes[i]);
            int id = pilotId%2 == 0 ? 0 : 1;

            System.out.println(" > PilotId: " + pilotId);

            Label labelPilot = new Label();
            labelPilot.setText((i+1) + ". " + teams[pilotId/2].getPilots()[id].getName() + " \t - \t Tempo de corrida: " + pilotTimes[i]);
            labelPilot.setFont(new Font("Arial", 12));

            this.vBoxResults.getChildren().addAll(labelPilot);
        }
    }

    public void nextGrandPrix(ActionEvent actionEvent) throws IOException, InterruptedException {
        if(DashboardController.getNextGrandPrix() == DashboardController.getChampionship().getNumOfRaces()-1) {
            this.stage.close();
            System.out.println(" > End of simulation :(");
        } else {
            this.stage.close();
            DashboardController.setNextGrandPrix(DashboardController.getNextGrandPrix() + 1);
            DashboardController.start();
        }
    }

    private static int indexOf(float[] array, float element) {
        int index = 0;
        System.out.println("==================================================");
        System.out.println(" Searching for " + element);

        for ( ; index < array.length; index++) {
            System.out.println(" > Item at index " + index + ": " + array[index]);
            if (array[index] == element) {
                array[index] = -1;
                break;
            }
        }
        System.out.println("Found at index " + index);
        System.out.println("==================================================");

        return index;
    }
}
