package controllers;

import db.DBController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class PodiumController implements Initializable {
        @FXML
        private Label secondPlaceLabel;

        @FXML
        private Label thirdPlaceLabel;

        @FXML
        private Label firstPlaceLabel;

        @FXML
        private Button goBackBtn;


        DBController dbController = new DBController();
        TreeMap<Integer, String> topThree = dbController.getTopThree();
        int score = 0;
        String name = null;


        public void initialize(URL url, ResourceBundle resourceBundle) {
                while(!topThree.isEmpty()){
                        switch(topThree.size()){
                                case 1:
                                        score = topThree.lastKey();
                                        name = topThree.get(score);
                                        drawThirdPlace(name, score);
                                        topThree.remove(score);
                                        break;
                                case 2:
                                        score = topThree.lastKey();
                                        name = topThree.get(score);
                                        drawSecondPlace(name, score);
                                        topThree.remove(score);
                                        break;
                                case 3:
                                        score = topThree.lastKey();
                                        name = topThree.get(score);
                                        drawFirstPlace(name, score);
                                        topThree.remove(score);
                                        break;
                        }
                }
        }
        public void drawFirstPlace(String name, int score){
                firstPlaceLabel.setText(name+" (" + score + ")");
        }
        public void drawSecondPlace(String name, int score){
                secondPlaceLabel.setText(name+" (" + score + ")");
        }
        public void drawThirdPlace(String name, int score){
                thirdPlaceLabel.setText(name+" (" + score + ")");
        }

        public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
                Parent scoresViewParent = FXMLLoader.load(getClass().getResource("../fxmls/welcome.fxml"));
                Scene scoresScene = new Scene(scoresViewParent);
                Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                window.setScene(scoresScene);
                window.show();
        }
}
