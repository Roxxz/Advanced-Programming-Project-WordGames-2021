package sample;

import db.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PodiumController implements Initializable {
        @FXML
        private Label secondPlaceLabel;

        @FXML
        private Label thirdPlaceLabel;

        @FXML
        private Label firstPlaceLabel;
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


}
