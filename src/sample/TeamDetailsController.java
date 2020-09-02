package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamDetailsController implements Initializable {
    public Label labelPilotName;
    public ChoiceBox<String> choicePilot;
    public Label labelNameMechanic01;
    public Label labelTimeChangingTyresMechanic01;
    public Label labelRefuelingMechanic01;
    public Label labelNameMechanic02;
    public Label labelTimeChangingTyresMechanic02;
    public Label labelRefuelingMechanic02;
    public Label labelNameMechanic03;
    public Label labelTimeChangingTyresMechanic03;
    public Label labelRefuelingMechanic03;
    public Label labelNameMechanic04;
    public Label labelTimeChangingTyresMechanic04;
    public Label labelRefuelingMechanic04;
    public Label labelNameEngineer;
    public Label laberCarId;
    public Label labelCarFuel;
    public Label labelCarStatus;
    public Label labelCarTyres;
    public Label labelTeamName;

    private Team team;
    public int pilotId;
    private Pilot pilot;
    public Stage stage;

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("==============================\n > Showing team details...");
        this.pilotId = 0;
        this.team = new Team();
        this.pilot = new Pilot();
    }

    public void init(Team team) {
        this.team = team;
        this.pilot = this.team.getPilots()[this.pilotId];

        this.setChoices();
        this.fillValues();
    }

    private void setChoices() {
        ObservableList<String> options = FXCollections.observableArrayList(this.team.getPilots()[0].getName(), this.team.getPilots()[1].getName());
        this.choicePilot.setItems(options);
        this.choicePilot.setValue(options.get(0));

        this.choicePilot.getSelectionModel().selectedIndexProperty().addListener((obs, oldId, newId) -> {
            System.out.println(" > Selected pilot: " + newId);
            this.pilot = this.team.getPilots()[(int) newId];
            this.fillValues();
        });
    }

    private void fillValues() {
        this.labelTeamName.setText(this.team.getName() + " - Id: " + this.team.getId());

        this.labelPilotName.setText(" " + this.pilot.getName() + " - Id: " + this.pilot.getId());

        this.labelNameMechanic01.setText(this.pilot.getMechanics()[0].getName());
        this.labelTimeChangingTyresMechanic01.setText("Tempo para trocar pneus: " + Float.toString(this.pilot.getMechanics()[0].getTimeChangingTyres()) + "s");
        this.labelRefuelingMechanic01.setText("Tempo de reabastecimento: " + Float.toString(this.pilot.getMechanics()[0].getTimeRefueling()) + "s");
        this.labelNameMechanic02.setText(this.pilot.getMechanics()[1].getName());
        this.labelTimeChangingTyresMechanic02.setText("Tempo para trocar pneus: " + Float.toString(this.pilot.getMechanics()[1].getTimeChangingTyres()) + "s");
        this.labelRefuelingMechanic02.setText("Tempo de reabastecimento: " +  Float.toString(this.pilot.getMechanics()[1].getTimeRefueling()) + "s");
        this.labelNameMechanic03.setText(this.pilot.getMechanics()[2].getName());
        this.labelTimeChangingTyresMechanic03.setText("Tempo para trocar pneus: " + Float.toString(this.pilot.getMechanics()[2].getTimeChangingTyres()) + "s");
        this.labelRefuelingMechanic03.setText("Tempo de reabastecimento: " +  Float.toString(this.pilot.getMechanics()[2].getTimeRefueling()) + "s");
        this.labelNameMechanic04.setText(this.pilot.getMechanics()[3].getName());
        this.labelTimeChangingTyresMechanic04.setText("Tempo para trocar pneus: " + Float.toString(this.pilot.getMechanics()[3].getTimeChangingTyres()) + "s");
        this.labelRefuelingMechanic04.setText("Tempo de reabastecimento: " +  Float.toString(this.pilot.getMechanics()[3].getTimeRefueling()) + "s");

        this.labelNameEngineer.setText(" " + this.pilot.getEngineer().getName() + " - Id: " + this.pilot.getEngineer().getId());

        this.laberCarId.setText("Carro " + this.pilot.getCar().getId());
        this.labelCarFuel.setText("Capacidade do tanque: " + this.pilot.getCar().getFuel() + " voltas");
        this.labelCarStatus.setText("Estado atual: " + this.pilot.getCar().getStatus());
        this.labelCarTyres.setText("Tipo de pneu: " + this.pilot.getCar().getTyres());


    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
