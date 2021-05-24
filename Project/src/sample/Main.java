package sample;

import db.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {

    static Connection connection;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Database db = new Database();
            connection = Database.getInstance().getConnection();
            connection.setAutoCommit(false);
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        Parent root = FXMLLoader.load(getClass().getResource("../fxmls/name.fxml"));
        primaryStage.setTitle("Spânzurătoarea");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}