package sample;

import com.sun.javafx.collections.MappingChange;
import db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class DBController {
    protected Connection connection = null;

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = Database.getInstance().getConnection();
        }
        return connection;
    }
    public DBController() {
        try {
            connection = getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public void insertNewPlayer(String name){
        try {
            String query = "insert into highscores(user_name)" +
                    " value(?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public boolean checkIfExists(String name){
        int number = 0;
        try {
            String query = "select count(*) as total from highscores where user_name = (?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                number = resultSet.getInt("total");
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        if(number == 0){
            return false;
        }
        return true;
    }
    public int getHighScore(String name){
        int highscore = 0;
        try {
            String query = "select highscore from highscores where user_name = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                highscore = resultSet.getInt("highscore");
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return highscore;
    }

    public void updateHighScore(String name, int score){
        try {
            String query = "update highscores set highscore = (?) where user_name = (?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, score);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public TreeMap<Integer, String> getTopThree(){
        TreeMap<Integer, String> podium = new TreeMap<Integer, String>();
        String name = null;
        int score = 0;
        try {
            String query = "select user_name, highscore from highscores order by highscore desc LIMIT 3;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("user_name");
                score = resultSet.getInt("highscore");
                podium.put(score, name);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return podium;
    }
}
