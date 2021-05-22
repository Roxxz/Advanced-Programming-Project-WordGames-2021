package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.*;

public class ScoresController {
    PlayController playController;

    @FXML
    private javafx.scene.control.Button idGoBackBtn;
    @FXML
    private TextFlow idHourList;
    @FXML
    private TextFlow idScoreList;

    ScoresController(){
        int highScore = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("res/score.txt"));
            String line = reader.readLine();
            while (line != null)
            {
                try {
                    int score = Integer.parseInt(line.trim());
                    if (score > highScore)
                    {
                        highScore = score;
                    }
                } catch (NumberFormatException e1) {
                    System.err.println("ignoring invalid score: " + line);
                }
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException ex) {
            System.err.println("ERROR reading scores from file");
        }
        // display the high score
        if (playController.getScore() > highScore){
            System.out.println("You now have the new high score! The previous high score was " + highScore);
        } else if (playController.getScore() == highScore) {
            System.out.println("You tied the high score!");
        } else {
            System.out.println("The all time high score was " + highScore);
        }


        // append the last score to the end of the file
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("res/score.txt", true));
            output.newLine();
            output.append("" + playController.getScore());
            output.close();

        } catch (IOException ex1) {
            System.out.printf("ERROR writing score to file: %s\n", ex1);
        }
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
