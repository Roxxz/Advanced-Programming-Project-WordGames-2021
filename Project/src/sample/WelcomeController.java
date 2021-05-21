package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private javafx.scene.control.Button idPlay;
    @FXML
    private javafx.scene.control.Button idScores;
    @FXML
    private javafx.scene.control.Button idQuit;


    public void playButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent playViewParent = FXMLLoader.load(getClass().getResource("Play.fxml"));
        Scene playScene = new Scene(playViewParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(playScene);
        window.show();
    }

    public void scoresButtonClicked(javafx.event.ActionEvent actionEvent) throws IOException  {
        Parent scoresViewParent = FXMLLoader.load(getClass().getResource("Scores.fxml"));
        Scene scoresScene = new Scene(scoresViewParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scoresScene);
        window.show();
    }

    public void quitButtonClicked() {
        Stage stage = (Stage) idQuit.getScene().getWindow();
        stage.close();
    }
}
