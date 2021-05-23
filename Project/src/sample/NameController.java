package sample;

import db.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.input.InputMethodEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;


public class NameController {
    @FXML
    private Label nameLabel;

    @FXML
    private Button startButton;

    @FXML
    private TextField inputField;
    public static String playersName;

    public void startButtonClicked(ActionEvent actionEvent) throws IOException {
        playersName = inputField.getText();
        if(playersName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atentie");
            alert.setHeaderText("Nu ati introdus un nume. ");
            alert.showAndWait();
        }else{
            DBController dbController = new DBController();
            dbController.insertNewPlayer(playersName);
            Parent playViewParent = FXMLLoader.load(getClass().getResource("welcome.fxml"));
            Scene playScene = new Scene(playViewParent);
            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(playScene);
            window.show();
        }

    }
    @FXML
    public void nameEntered(InputMethodEvent event) {

    }

}

