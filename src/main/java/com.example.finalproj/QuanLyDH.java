package com.example.finalproj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class QuanLyDH {
    // Thêm đơn hàng
    public static void themDonHang(String maDH, String tenKH, Date ngayTao) throws ClassNotFoundException {
        String sql = "INSERT INTO donhang (maDH, tenKH, ngayTao) VALUES (?, ?, ?)";
        try (Connection conn = MySQLConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maDH);
            pstmt.setString(2, tenKH);
            pstmt.setDate(3, ngayTao);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật đơn hàng theo mã đơn hàng
    public static void capNhatDonHang(String maDH, String tenKH) throws ClassNotFoundException {
        String sql = "UPDATE donhang SET tenKH = ? WHERE maDH = ?";
        try (Connection conn = MySQLConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tenKH);
            pstmt.setString(2, maDH);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa đơn hàng theo mã đơn hàng
    public static void xoaDonHang(String maDH) throws ClassNotFoundException {
        String sql = "DELETE FROM donhang WHERE maDH = ?";
        try (Connection conn = MySQLConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maDH);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Thêm sản phẩm vào đơn hàng
    public static void themSanPhamVaoDonHang(String maDH, String maSP, int soLuong) throws ClassNotFoundException {
        String sql = "INSERT INTO order_product (order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = MySQLConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maDH);
            pstmt.setString(2, maSP);
            pstmt.setInt(3, soLuong);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy danh sách đơn hàng
    public static List<String> layDanhSachDonHang() throws ClassNotFoundException {
        String sql = "SELECT dh.maDH, dh.tenKH, sp.tenSP, od.quantity, sp.giaSP, (sp.giaSP * od.quantity) AS thanhTien "
                + "FROM donhang dh "
                + "JOIN order_product od ON dh.maDH = od.order_id "
                + "JOIN sanpham sp ON od.product_id = sp.maSP";
        List<String> danhSachDonHang = new ArrayList<>();
        try (Connection conn = MySQLConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            StringBuilder builder = new StringBuilder();
            String currentMaDH = "";

            while (rs.next()) {
                String maDH = rs.getString("maDH");
                String tenKH = rs.getString("tenKH");
                String tenSP = rs.getString("tenSP");
                int soLuong = rs.getInt("quantity");
                double giaSP = rs.getDouble("giaSP");
                double thanhTien = rs.getDouble("thanhTien");

                if (!maDH.equals(currentMaDH)) {
                    if (!currentMaDH.isEmpty()) {
                        danhSachDonHang.add(builder.toString());
                        builder.setLength(0);
                    }
                    builder.append("Đơn hàng: ").append(maDH)
                            .append(" - Tên KH: ").append(tenKH)
                            .append("\nSản phẩm:\n");
                    currentMaDH = maDH;
                }

                builder.append("- ").append(tenSP)
                        .append(", SL: ").append(soLuong)
                        .append(", Giá: ").append(giaSP)
                        .append(", Thành tiền: ").append(thanhTien)
                        .append("\n");
            }
            if (builder.length() > 0) {
                danhSachDonHang.add(builder.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachDonHang;
    }
}
