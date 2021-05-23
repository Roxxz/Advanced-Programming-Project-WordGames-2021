package db;

import java.sql.*;
public class Database {
    private static Database instance;
    private Connection connection;

    private final String URL = "jdbc:mysql://localhost:3306/hangman";
    private final String USERNAME = "root";
    private final String PASSWORD = "";



    public Database() {
        try {
            String mysqlDriver = "com.mysql.jdbc.Driver";
            Class.forName(mysqlDriver);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("db.Database Connection Creation Failed: " + exception.getMessage());
        }
    }
    public void tryConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection =DriverManager.getConnection(
                    URL, USERNAME, PASSWORD);
        }catch(Exception e){ System.out.println(e);}
    }


    public Connection getConnection() {
        return connection;
    }

    /**
     * Restricts the instantiation of Database class
     * to one "single" instance
     */
    public static Database getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new Database();
        }

        return instance;
    }
}