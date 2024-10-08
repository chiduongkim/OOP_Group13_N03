package MySqlExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertProductExample {
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
                     "INSERT INTO sanpham (maSP, tenSP, giaSP, soLuong) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, maSP);
            preparedStatement.setString(2, tenSP);
            preparedStatement.setDouble(3, Double.parseDouble(giaSP));
            preparedStatement.setInt(4, Integer.parseInt(soLuong));

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

