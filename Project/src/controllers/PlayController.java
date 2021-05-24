package controllers;

import db.DBController;
import dictionary.DexSearch;
import dictionary.SynonymSearch;
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
import game.Game;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static game.Game.*;
import static controllers.NameController.playersName;
import static controllers.ChooseGameController.typeOfGame;
import static controllers.ScoresController.*;

public class PlayController implements Initializable  {
    Game game;
    public static int score = 0;
    private int lives = 8;

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
                    try {
                        checkGameOver();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    idLetterInserted.clear();
                }
            }
        });
        updateWord();
    }

    private void checkGameOver() throws IOException {
        if (game.isGameWon()) {
            // Return game won message
            lives = 8;
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
            Date date = new Date();
            scoresList.put(dateFormat.format(date), score);
            int score = getScore();
            DBController dbController = new DBController();
            if(score > dbController.getHighScore(playersName)){
                dbController.updateHighScore(playersName, score);
            }
            String word = this.game.getRandomWord();
            DexSearch dexSearch = new DexSearch();
            dexSearch.findWord(word);
            dexSearch.openPage();
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
        this.score = this.getScore() + points;
        switch(typeOfGame){
            case 2:
                this.score = this.getScore() + 1;
                break;
            case 3:
                this.score = this.getScore() + 3;
                break;
            default:
                break;
        }
        idScore.setText(String.valueOf(this.getScore()));
    }

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("../fxmls/welcome.fxml"));
        Scene homeScene = new Scene(homeViewParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(homeScene);
        window.show();
    }

    public void getDefButtonClicked(ActionEvent actionEvent) throws IOException {
        String word = this.game.getRandomWord();
        DexSearch dexSearch = new DexSearch();
        ArrayList<String> definition = dexSearch.findWord(word);
        StringBuilder builder = new StringBuilder();
        for (String value : definition) {
            builder.append(value);
            builder.append(" ");
        }
        String text = builder.toString();
        if(text.length() > 1000){
            text = text.substring(0, Math.min(text.length(), 1000));
            text = text + "...";
        }
        if(this.getScore() >= 10){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hint");
            alert.setHeaderText("Definitie pentru cuvantul dat: ");
            if(definition.size() == 0){
                alert.setContentText("Nu s-a gasit definitie pentru acest cuvant.");
            }
            else{
                alert.setContentText(text);
                setScore(-10);
            }
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atentie");
            alert.setHeaderText("Nu aveti suficiente puncte. ");
            alert.showAndWait();
        }
    }

    public void getSynButtonClicked(ActionEvent actionEvent) throws IOException {
        String word = this.game.getRandomWord();
        SynonymSearch synonymSearch = new SynonymSearch();
        ArrayList<String> synonyms = synonymSearch.findSynonym(word);
        StringBuilder builder = new StringBuilder();
        for (String value : synonyms) {
            builder.append(value);
            builder.append(" ");
        }
        String text = builder.toString();
        if(this.getScore() < 15){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atentie");
            alert.setHeaderText("Nu aveti suficiente puncte. ");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hint");
            alert.setHeaderText("Sinonime pentru cuvantul dat: ");
            if(synonyms.size() == 0){
                alert.setContentText("Acest cuvant nu are sinonim.");
            } else{
                alert.setContentText(String.valueOf(text));
                setScore(-15);
            }

            alert.showAndWait();
        }
    }

    public void nextButtonClicked(ActionEvent actionEvent) {
        try {
            game.reset();
            idLetterInserted.setDisable(false);
            idNextBtn.setDisable(true);
            updateWord();
            draw(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getScore() {
        return score;
    }
}
