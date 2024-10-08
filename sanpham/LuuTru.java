package sanpham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LuuTru {
    public static void luuThongTinSP(String maSP, String tenSP, String giaSP, String soLuong) {
        String host = "mysql-e4643ae-group13.i.aivencloud.com";
        String port = "13698";
        String databaseName = "defaultdb";
        String userName = "avnadmin";
        String password = "AVNS_fm4xvgmqE69OhX8l1GY";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require", 
                    userName, 
                    password);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO sanpham (maSP, tenSP, giaSP, soluong) VALUES (?, ?, ?, ?)")) {

                preparedStatement.setString(1, maSP);
                preparedStatement.setString(2, tenSP);
                preparedStatement.setDouble(3, Double.parseDouble(giaSP));
                preparedStatement.setInt(4, Integer.parseInt(soLuong));

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Sản phẩm mới đã được thêm thành công!");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Kết nối hoặc truy vấn SQL bị lỗi.");
            e.printStackTrace();
        }
    }
}
