package sanpham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LuuTru {
    public static void luuThongTinDH(String maDH, String maKH) {
        String host = "mysql-e4643ae-group13.i.aivencloud.com";
        String port = "13698";
        String databaseName = "defaultdb";
        String userName = "avnadmin";
        String password = "AVNS_fm4xvgmqE69OhX8l1GY";

        try {
            // Nạp driver MySQL JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Thiết lập kết nối với cơ sở dữ liệu
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require", 
                    userName, 
                    password);
                 
                 // Câu lệnh SQL để thêm đơn hàng
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO donhang (maDH, maKH) VALUES (?, ?)")) {

                // Đặt giá trị cho các tham số trong câu lệnh SQL
                preparedStatement.setString(1, maDH);
                preparedStatement.setString(2, maKH);
                // Thực thi câu lệnh và kiểm tra số dòng đã được thêm
                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Đơn hàng mới đã được thêm thành công!");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Xử lý các lỗi liên quan đến kết nối và câu lệnh SQL
            System.out.println("Kết nối hoặc truy vấn SQL bị lỗi.");
            e.printStackTrace();
        }
    }


	public static void luuThongTinSP(String maSP, String tenSP, String giaSP, String soLuong) {
		// TODO Auto-generated method stub
		
	}
}
