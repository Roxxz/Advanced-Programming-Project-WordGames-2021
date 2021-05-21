package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Game.*;

public class PlayController implements Initializable {
    private Game game;
    private int score=0;
    private int lives = 7;

    @FXML
    private javafx.scene.control.Label idScore;
    @FXML
    private javafx.scene.control.Label idLivesLeft;
    @FXML
    private javafx.scene.control.Label idWordToFind;
    @FXML
    private javafx.scene.control.TextField idLetterInserted;
    @FXML
    private javafx.scene.control.Label idWrongLetter;
    @FXML
    private javafx.scene.control.Button idGoBackBtn;
    @FXML
    private javafx.scene.image.ImageView idPicture;
    @FXML
    private javafx.scene.control.Button idNextBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.game = new Game();
        this.score = 0;
        System.out.println("Initializing game...");
        idWrongLetter.setText("_");
        idLivesLeft.setText(String.valueOf(lives));
        draw(0);
        idNextBtn.setDisable(true);
        startTextFieldListening();

    }

    private void startTextFieldListening(){
        idLetterInserted.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if(newValue.length() > 0)
                {
                        boolean rightMove = game.makeMove(newValue.toLowerCase().charAt(0));
                        if (rightMove) {
                            lives--;
                            idLivesLeft.setText(String.valueOf(lives));
                            updateWrongLetters();
                        }
                    draw(wrongGuesses);
                    updateWord();
                    checkGameOver();
                    idLetterInserted.clear();
                }
            }
        });
        updateWord();
    }

    private void checkGameOver() {
        if (game.isGameWon()) {
            // Return game won message
            lives = 7;
            draw(9);
            setScore(10);
            idLivesLeft.setText(String.valueOf(lives));
            idLetterInserted.setDisable(true);
            idWordToFind.setText("");
            idWrongLetter.setText("");
            idNextBtn.setDisable(false);
            idNextBtn.setOnAction(this::nextButtonClicked);

        } else if (game.isGameOver()) {
            // Return game over message
            idLetterInserted.setDisable(true);
            draw(8);
            drawCorrectWord();
        }
    }

    public void draw(int wrongGuesses){
        switch (wrongGuesses) {
            case 0:
                idPicture.setImage(new Image("/pics/img1.png"));
                break;
            case 1:
                idPicture.setImage(new Image("/pics/img2.png"));
                break;
            case 2:
                idPicture.setImage(new Image("/pics/img3.png"));
                break;
            case 3:
                idPicture.setImage(new Image("/pics/img4.png"));
                break;
            case 4:
                idPicture.setImage(new Image("/pics/img5.png"));
                break;
            case 5:
                idPicture.setImage(new Image("/pics/img6.png"));
                break;
            case 6:
                idPicture.setImage(new Image("/pics/img7.png"));
                break;
            case 7:
                idPicture.setImage(new Image("/pics/img8.png"));
                break;
            case 8:
                idPicture.setImage(new Image("/pics/imgLost.png"));
                break;
            case 9:
                idPicture.setImage(new Image("/pics/imgWin.png"));
                break;
        }
    }

    private void drawCorrectWord() {
        idWordToFind.setText(game.getMissingChars());
    }

    private void updateWord() {
        idWordToFind.setText(game.getCurrentWord());
    }

    private void updateWrongLetters() {
        StringBuilder enteredFormatted = new StringBuilder();
        game.getWrongLetters().stream().sorted().forEach(i -> enteredFormatted.append(i).append(","));
        idWrongLetter.setText(enteredFormatted.toString().substring(0, enteredFormatted.length() - 1));
    }

    private void setScore(int points){
        this.score += points;
        idScore.setText(String.valueOf(this.score));
    }

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene homeScene = new Scene(homeViewParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(homeScene);
        window.show();
    }

    public void getDefButtonClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hint");
        alert.setHeaderText("Definition:");
        alert.setContentText("something something something");

        alert.showAndWait();
    }

    public void getSynButtonClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hint");
        alert.setHeaderText("Synonyms:");
        alert.setContentText("something something something");

        alert.showAndWait();
    }

    public void nextButtonClicked(ActionEvent actionEvent) {
        try {
            game.reset();
            idLetterInserted.setDisable(false);
            updateWord();
            draw(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
