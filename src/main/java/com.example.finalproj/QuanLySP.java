package com.example.finalproj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuanLySP {
    //Thêm SP
    public static void themSanPham(String maSP, String tenSP, double giaSP, int soLuong) throws ClassNotFoundException {
        String sql = "INSERT INTO sanpham (maSP, tenSP, giaSP, soLuong) VALUES (?, ?, ?, ?)";
        try (Connection conn = MySQLConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maSP);
            pstmt.setString(2, tenSP);
            pstmt.setDouble(3, giaSP);
            pstmt.setInt(4, soLuong);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Cập nhật thông tin sản phẩm qua mã SP
    public static void capNhatSanPham(String maSP, String tenSP, double giaSP, int soLuong) throws ClassNotFoundException {
        String sql = "UPDATE sanpham SET tenSP = ?, giaSP = ?, soLuong = ? WHERE maSP = ?";
        try (Connection conn = MySQLConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tenSP);
            pstmt.setDouble(2, giaSP);
            pstmt.setInt(3, soLuong);
            pstmt.setString(4, maSP);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Cập nhật thông tin sản phẩm qua mã SP
    public static void xoaSanPham(String maSP) throws ClassNotFoundException {
        String sql = "DELETE FROM sanpham WHERE maSP = ?";
        try (Connection conn = MySQLConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maSP);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Lấy danh sách sản phẩm
    public static List<String> layDanhSachSanPham() throws ClassNotFoundException {
        String sql = "SELECT * FROM sanpham";
        List<String> danhSachSanPham = new ArrayList<>();
        try (Connection conn = MySQLConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String sanPham = rs.getString("maSP") + " - " + rs.getString("tenSP") + " - " +
                        rs.getDouble("giaSP") + " - " + rs.getInt("soLuong");
                danhSachSanPham.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachSanPham;
    }
}
