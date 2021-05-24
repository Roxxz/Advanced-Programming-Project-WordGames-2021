package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class ScoresController implements Initializable {
    public static Map<String, Integer> scoresList = new HashMap<>();

    @FXML
    private javafx.scene.control.Button idGoBackBtn;
    @FXML
    private TextArea idHourList;
    @FXML
    private TextArea idScoreList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(!scoresList.isEmpty()){
            for(Map.Entry<String, Integer> entry : scoresList.entrySet()) {
                try {
                    BufferedWriter output = new BufferedWriter(new FileWriter("src/sample/res/score.txt", true));
                    output.newLine();
                    output.append("" + entry);
                    output.close();

                } catch (IOException ex1) {
                    System.out.printf("ERROR writing score to file: %s\n", ex1);
                }
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/sample/res/score.txt"));
            String line = reader.readLine();
            while (line != null)
            {
                try {
                    int score = Integer.parseInt(line.trim().substring(18,line.length()));
                    String date = line.trim().substring(0, 17);
                    scoresList.put(date, score);
                } catch (NumberFormatException e1) {
                    System.err.println("ignoring invalid score: " + line);
                }
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException ex) {
            System.err.println("ERROR reading scores from file");
            ex.printStackTrace();
        }
        showScoresList();
    }

    private void showScoresList() {

        for(String date: scoresList.keySet()){
            idHourList.appendText("\n" + date);
        }
        for(Integer score: scoresList.values()){
            idScoreList.appendText("\n" + score);
        }
        scoresList.clear();
    }

    @FXML
    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene homeScene = new Scene(homeViewParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(homeScene);
        window.show();
    }

}
