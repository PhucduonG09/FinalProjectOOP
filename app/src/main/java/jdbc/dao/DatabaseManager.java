package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.models.Habit;
import jdbc.models.HabitFrequency;

public class DatabaseManager {
    public static void main(String[] args) {
        getConnection();
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/habit_db";
        String user = "root";
        String password = "Phuc2006@";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connect Successfull");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static Connection connect() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connect'");
    }

    // public static List<Habit> getUser() {
    //     Connection con = getConnection();
    //     try {
    //         Statement stmt = con.prepareStatement("select * form habits;");
    //         ResultSet rs = stmt.executeQuery("select * from habits;");
    //         List<Habit> habits = new ArrayList<>();
    //         while (rs.next()) {
    //             int id = rs.getInt("id");
    //             String name = rs.getString("name");
    //             String goal = rs.getString("goal");
    //             HabitFrequency frequency = ((HabitFrequency) rs);
    //             Habit newHabit = new Habit(id)
    //         }
    //     }
    // }
}
