package com.example.finalproj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionUtil {
    // Thông tin CSDL
    private static final String HOST = "mysql-821a80-group13oop.i.aivencloud.com";
    private static final String PORT = "17517";
    private static final String DATABASE_NAME = "defaultdb";
    private static final String USER_NAME = "avnadmin";
    private static final String PASSWORD = "AVNS_FvusARuAbNk7drxw3sn";

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE_NAME + "?useSSL=true&requireSSL=true";
    //Kết nối CSDL
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Kết nối thành công!");

        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi: Trình điều khiển JDBC không được tìm thấy!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Lỗi: Không thể kết nối đến cơ sở dữ liệu!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
