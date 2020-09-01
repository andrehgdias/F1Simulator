package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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

    private int numOfRaces = 5;

    public TextField fieldChampionshipName;
    public Label labelNumOfRaces;
    public TextField fieldPenaltyTime;
    public FlowPane paneTeams;
    public FlowPane paneCities;
    public Button buttonSaveChampionship;
    public Button buttonAddGrandprix;


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
            this.enableButton(this.buttonSaveChampionship, true);
        });
        this.fieldChampionshipName.textProperty().addListener((obs, oldText, newText) -> {
            System.out.println("Text changed from "+oldText+" to "+newText);
            this.enableButton(this.buttonSaveChampionship, true);
        });
        this.enableButton(this.buttonSaveChampionship, false);


        this.paneCities.setMinWidth(this.numOfRaces * 150);
        this.paneTeams.setMinWidth(this.numOfRaces * 150);
        this.generateGrandPrix(this.numOfRaces);
        this.drawGrandPrixsCards(false);
    }

    private void generateGrandPrix(int numOfRaces) {
        Random random = new Random();
        ArrayList<GrandPrix> grandPrixs;
        int i = 1;
        if(numOfRaces == 1) {
            grandPrixs = this.championship.getGrandPrixs();
            i = ++this.numOfRaces;
        } else {
            grandPrixs = new ArrayList<GrandPrix>();
        }
        for ( ; i <= this.numOfRaces; i++) {
            String weather;
            int option = random.nextInt(3);
            if (option == 0) weather = "Limpo";
            else if (option == 1) weather = "Nublado";
            else weather = "Chuvoso";
            grandPrixs.add(new GrandPrix(this.championship, "City " + i, 5 * i, 10 * i, weather));
        }
        this.championship.setGrandPrixs(grandPrixs);
    }

    private void drawGrandPrixsCards(boolean addOneCard) {
        int i = addOneCard? this.numOfRaces - 1 :  0;
        ArrayList<GrandPrix> grandPrixs = this.championship.getGrandPrixs();
        for (; i < this.numOfRaces; i++) {
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
        this.labelNumOfRaces.setText(Integer.toString(this.numOfRaces) + "/9");
    }
    
    public void enableButton(Button button, boolean status) {
        button.setDisable(!status);
        System.out.println(" > Save Button Disabled: " + button.isDisabled());
    }

    public void saveChampionship(ActionEvent actionEvent) {
        this.championship.setName(this.fieldChampionshipName.getText());
        this.championship.setPenaltyTime(Float.parseFloat(this.fieldPenaltyTime.getText()));
        this.enableButton(this.buttonSaveChampionship,false);
        this.fillValues();
    }

    public void addGrandPrixs(ActionEvent actionEvent) {
        this.generateGrandPrix(1);
        this.drawGrandPrixsCards(true);
        this.fillValues();
        if (this.numOfRaces == 9) this.enableButton(this.buttonAddGrandprix,false);
    }

    public void openEditTeams(ActionEvent actionEvent) {
    }

    public void start(ActionEvent actionEvent) {
    }


}
