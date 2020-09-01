package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {

    Desktop desktop = Desktop.getDesktop();

    private int numOfRaces = 7;

    public javafx.scene.control.Button buttonSaveChampionship;
    public TextField fieldChampionshipName;
    public Label labelNumOfRaces;
    public TextField fieldPenaltyTime;
    public FlowPane paneTeams;
    public FlowPane paneCities;

    private Championship championship = new Championship("Campeonato Mundial", 8.0f);

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public void openLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        try {
            desktop.browse(new URI("https://andredias.dev.br"));
        } catch (IOException exc) {
            System.out.println("Não foi possível abrir o link!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("==============================\n > Initializing Dashboard...");

        this.fillValues();
        this.fieldPenaltyTime.textProperty().addListener((obs, oldText, newText) -> {
            System.out.println("Text changed from "+oldText+" to "+newText);
            this.enableButton(true);
        });
        this.fieldChampionshipName.textProperty().addListener((obs, oldText, newText) -> {
            System.out.println("Text changed from "+oldText+" to "+newText);
            this.enableButton(true);
        });
        this.enableButton(false);


        this.paneCities.setMinWidth(this.numOfRaces * 130);
        this.paneTeams.setMinWidth(this.numOfRaces * 130);
        this.generateGrandPrix();
        this.drawGrandPrixsCards();
    }

    private void generateGrandPrix() {
        Random gerador = new Random();
        ArrayList<GrandPrix> grandPrixs = new ArrayList<GrandPrix>();
        for (int i = 1; i <= this.numOfRaces; i++) {
            String weather;
            int option = gerador.nextInt(3);
            if(option == 0) weather = "Limpo";
            else if (option == 1) weather= "Nublado";
            else weather = "Chuvoso";

            grandPrixs.add(new GrandPrix(this.championship,"City " + i, 5*i, 10*i, weather));
        }
        this.championship.setGrandPrixs(grandPrixs);
    }

    private void drawGrandPrixsCards() {
        ArrayList<GrandPrix> grandPrixs = this.championship.getGrandPrixs();
        for (int i = 0; i < this.numOfRaces; i++) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: #606060;");
            card.setMinSize(100,60);
            card.setSpacing(5);
            card.setPadding(new Insets(10,20,10,20));

            Label labelCity = new Label();
            labelCity.setText("Grande Prêmio de "+ grandPrixs.get(i).getCity());
            labelCity.setFont(new Font("Arial", 14));

            Label labelLaps = new Label();
            labelLaps.setText("Nº de voltas: " + grandPrixs.get(i).getLaps() + " - " + grandPrixs.get(i).getDefaultLapTime() +"s/volta");
            labelLaps.setFont(new Font("Arial", 12));

            Label labelWeather = new Label();
            labelWeather.setText("Clima inicial: "+ grandPrixs.get(i).getWeather());
            labelWeather.setFont(new Font("Arial", 12));


            card.getChildren().addAll(labelCity, labelLaps, labelWeather);
            paneCities.getChildren().add(card);
        }
    }

    public void fillValues() {
        this.fieldChampionshipName.setText(this.championship.getName());
        this.fieldPenaltyTime.setText(Float.toString(this.championship.getPenaltyTime()));
        this.labelNumOfRaces.setText(Integer.toString(this.numOfRaces));
    }
    
    public void enableButton(boolean status) {
        this.buttonSaveChampionship.setDisable(!status);
        System.out.println(" > Save Button Disabled: " + this.buttonSaveChampionship.isDisabled());
    }

    public void saveChampionship(ActionEvent actionEvent) {
        this.championship.setName(this.fieldChampionshipName.getText());
        this.championship.setPenaltyTime(Float.parseFloat(this.fieldPenaltyTime.getText()));
        this.enableButton(false);
        this.fillValues();
    }

    public void openEditGrandPrixs(ActionEvent actionEvent) {
    }

    public void openEditTeams(ActionEvent actionEvent) {
    }

    public void start(ActionEvent actionEvent) {
    }
}
