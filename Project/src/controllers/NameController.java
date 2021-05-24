package controllers;

import db.DBController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.scene.input.InputMethodEvent;
import java.io.IOException;


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
            if(dbController.checkIfExists(playersName)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Acest nume este deja folosit.");
                alert.setContentText("Scorul va fi modificat doar daca il va intrece pe cel anterior. Continuati?");
                ButtonType okButton = new ButtonType("Da", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("Nu", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().setAll(okButton, noButton);
                alert.showAndWait().ifPresent(type -> {
                    if (type == okButton) {
                        Parent playViewParent = null;
                        try {
                            playViewParent = FXMLLoader.load(getClass().getResource("../fxmls/welcome.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene playScene = new Scene(playViewParent);
                        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                        window.setScene(playScene);
                        window.show();
                    }
                });
            } else{
                dbController.insertNewPlayer(playersName);
                Parent playViewParent = FXMLLoader.load(getClass().getResource("../fxmls/welcome.fxml"));
                Scene playScene = new Scene(playViewParent);
                Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                window.setScene(playScene);
                window.show();
            }

        }

    }
    @FXML
    public void nameEntered(InputMethodEvent event) {

    }

}

