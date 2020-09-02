package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class MenuController {
    public TextField fieldChampionshipName;
    public Label labelNumOfRaces;
    public TextField fieldPenaltyTime;
    Desktop desktop = Desktop.getDesktop();
    private Stage mainStage;

    public void setStage(Stage stage) {
        mainStage = stage;
    }

    public void openLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        try {
        desktop.browse(new URI("https://andredias.dev.br"));
        } catch (IOException exc) {
            System.out.println("Não foi possível abrir o link!");
        }
    }

    public void openDashboard(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        mainStage.setTitle("F1 Simulator Dashboard");
        mainStage.setScene(new Scene(root));
    }
}
