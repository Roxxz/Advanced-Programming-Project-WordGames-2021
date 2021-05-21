package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController {

    @FXML
    private javafx.scene.control.Button idPlay;
    @FXML
    private javafx.scene.control.Button idLevels;
    @FXML
    private javafx.scene.control.Button idQuit;


    public void playButtonClicked(ActionEvent actionEvent) throws IOException {
        final Game game = new Game();
        Parent playViewParent = FXMLLoader.load(getClass().getResource("Play.fxml"));
        Scene playScene = new Scene(playViewParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(playScene);
        window.show();
    }

    public void levelsButtonClicked(javafx.event.ActionEvent actionEvent) throws IOException  {
        Parent levelsViewParent = FXMLLoader.load(getClass().getResource("Levels.fxml"));
        Scene levelsScene = new Scene(levelsViewParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(levelsScene);
        window.show();
    }

    public void quitButtonClicked() {
        Stage stage = (Stage) idQuit.getScene().getWindow();
        stage.close();
    }
}
