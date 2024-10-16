package donhang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertOrderExample {
    public static void main(String[] args) throws ClassNotFoundException {
        String host = "mysql-e4643ae-group13.i.aivencloud.com";
        String port = "13698";
        String databaseName = "defaultdb";
        String userName = "avnadmin";
        String password = "AVNS_fm4xvgmqE69OhX8l1GY";

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (final Connection connection = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require", 
                userName, 
                password);
             final PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO donhang (maDH, maKH) VALUES (?, ?)")) {
            String maDH = null;
            String maKH = null;
			preparedStatement.setString(1, maDH);
            preparedStatement.setString(2, maKH);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new product was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Connection or SQL execution failure.");
            e.printStackTrace();
        }
    }
}

