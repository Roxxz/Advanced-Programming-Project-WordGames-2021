package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class PlayController implements Initializable {
    private Game game;

    @FXML
    private javafx.scene.control.Label idLevel;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.game = new Game();
        idWrongLetter.setText("");
        int lives = 7;
        idLivesLeft.setText(String.valueOf(lives));

        idLetterInserted.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if("".equalsIgnoreCase(newValue)){
                return;
            }
            char ch = newValue.charAt(0);

            if (game.addChar(ch)) {
                // update picture
                int wrongGuesses = game.getWrongGuesses();
                idLivesLeft.setText(String.valueOf(lives - 1));
                draw(wrongGuesses);
            }
            idLetterInserted.clear();
            updateEnteredChars();
            updateWord();
            checkGameOver();
        });

        updateWord();
    }

    private void checkGameOver() {
        if (game.isGameWon()) {
            // Return game won message
            disableGame();
            draw(9);

        } else if (game.isGameOver()) {
            // Return game over message
            disableGame();
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

    private void disableGame() {
        idLetterInserted.setDisable(true);
    }

    private void updateWord() {
        idWordToFind.setText(game.getCurrentWord());
    }

    private void updateEnteredChars() {
        StringBuilder enteredFormatted = new StringBuilder();
        game.getEnteredChars().stream().sorted().forEach(i -> enteredFormatted.append(i).append(","));
        idWrongLetter.setText(enteredFormatted.toString().substring(0, enteredFormatted.length() - 1));
    }

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene homeScene = new Scene(homeViewParent);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(homeScene);
        window.show();
    }

}
