package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.Cursor;
import javafx.stage.Stage;

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


    private static Championship championship = new Championship("Campeonato Mundial", 8.0f);

    public static Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        DashboardController.championship = championship;
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


        this.paneCities.setMinWidth(this.numOfRaces * 130);
        this.paneTeams.setMinWidth(this.numOfRaces * 130);
        this.generateGrandPrix(this.numOfRaces);
        this.drawGrandPrixsCards(false);
        this.generateTeams();
        this.drawTeamsCards();
    }

    private void generateTeams() {
        Team[] teams = new Team[10];
        for (int i = 0; i < 10; i++) {
            Car[] cars = new Car[2];
            Pilot[] pilots = new Pilot[2];

            teams[i] = new Team(i, "Equipe " + (i+1));

            for (int j = 0; j < 2; j++) {
                Mechanic[] mechanics = new Mechanic[4];
                cars[j] = new Car(j, teams[i], 10, "Pronto", "Duro");
                pilots[j] = new Pilot(j, "Piloto " + teams[i].getName() + " " + (j+1), cars[j]);
                pilots[j].setEngineer(new Engineer(0, "Engenheiro "  + pilots[j].getName(), pilots[j]));
                for (int k = 0; k < 4; k++) {
                    mechanics[k] = new Mechanic(k, "Mecânico " + pilots[j].getName() + " " + (k+1) , pilots[j] , 2*(k+1), (float)1.5*(k+1));
                }
                pilots[j].setMechanics(mechanics);
                cars[j].setPilot(pilots[j]);
            }
            teams[i].setCars(cars);
            teams[i].setPilots(pilots);
        }
        DashboardController.championship.setTeams(teams);
        System.out.println(" > All 10 teams were genarated!");
    }

    private void drawTeamsCards() {
        Team[] teams  = DashboardController.championship.getTeams();
        for (Team team : teams) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: #606060;");
            card.setMinSize(140, 60);
            card.setSpacing(5);
            card.setPadding(new Insets(10, 20, 10, 20));
            card.setCursor(Cursor.HAND);
            card.setId(Integer.toString(team.getId()));

            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                String id = "0";
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (((Node) mouseEvent.getTarget()).getParent().getClass() == Label.class) {
                        id = ((Node) mouseEvent.getTarget()).getParent().getParent().getId();
                    } else {
                        id = ((Node) mouseEvent.getTarget()).getId();
                    }
                    try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamDetails.fxml"));
                        Parent root = loader.load();
                        System.out.println("Loader Root:");
                        System.out.println(root);
                        TeamDetailsController controller = loader.getController();
                        Stage stage = new Stage();
                        stage.setTitle("Detalhes " + team.getName());
                        Scene scene= new Scene(root);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                        controller.setStage(stage);
                        controller.init(team);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            Label labelTeam = new Label();
            labelTeam.setText("Id: " + team.getId() + " - " + team.getName());
            labelTeam.setFont(new Font("Arial", 14));

            Label labelPilot01 = new Label();
            labelPilot01.setText(team.getPilots()[0].getName());
            labelPilot01.setFont(new Font("Arial", 12));

            Label labelPilot02 = new Label();
            labelPilot02.setText(team.getPilots()[1].getName());
            labelPilot02.setFont(new Font("Arial", 12));

            card.getChildren().addAll(labelTeam, labelPilot01, labelPilot02);
            paneTeams.getChildren().add(card);
        }
    }

    private void generateGrandPrix(int numOfRaces) {
        Random random = new Random();
        ArrayList<GrandPrix> grandPrixs;
        int i = 1;
        if(numOfRaces == 1) {
            grandPrixs = DashboardController.championship.getGrandPrixs();
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
            grandPrixs.add(new GrandPrix(DashboardController.championship, "City " + i, 5 * i, 10 * i, weather));
        }
        DashboardController.championship.setGrandPrixs(grandPrixs);
    }

    private void drawGrandPrixsCards(boolean addOneCard) {
        int i = addOneCard? this.numOfRaces - 1 :  0;
        ArrayList<GrandPrix> grandPrixs = DashboardController.championship.getGrandPrixs();
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
        this.fieldChampionshipName.setText(DashboardController.championship.getName());
        this.fieldPenaltyTime.setText(Float.toString(DashboardController.championship.getPenaltyTime()));
        this.labelNumOfRaces.setText(Integer.toString(this.numOfRaces) + "/9");
    }
    
    public void enableButton(Button button, boolean status) {
        button.setDisable(!status);
        System.out.println(" > Save Button Disabled: " + button.isDisabled());
    }

    public void saveChampionship(ActionEvent actionEvent) {
        DashboardController.championship.setName(this.fieldChampionshipName.getText());
        DashboardController.championship.setPenaltyTime(Float.parseFloat(this.fieldPenaltyTime.getText()));
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
