package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseGameController {
    public static int typeOfGame;

    @FXML
    private Button beginnerID;

    @FXML
    private Button mediumID;

    @FXML
    private Button advancedID;

    @FXML
    private javafx.scene.control.Button idGoBackBtn;


    public void beginnerButtonClicked(ActionEvent actionEvent) throws IOException {
        typeOfGame = 1;
        Parent playViewParent = FXMLLoader.load(getClass().getResource("Play.fxml"));
        Scene playScene = new Scene(playViewParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(playScene);
        window.show();
    }


    public void mediumButtonClicked(ActionEvent actionEvent) throws IOException {
        typeOfGame = 2;
        Parent playViewParent = FXMLLoader.load(getClass().getResource("Play.fxml"));
        Scene playScene = new Scene(playViewParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(playScene);
        window.show();
    }

    public void advancedButtonClicked(ActionEvent actionEvent) throws IOException {
        typeOfGame = 3;
        Parent playViewParent = FXMLLoader.load(getClass().getResource("Play.fxml"));
        Scene playScene = new Scene(playViewParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(playScene);
        window.show();
    }

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent playViewParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene playScene = new Scene(playViewParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(playScene);
        window.show();
    }
}
