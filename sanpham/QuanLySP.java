package sanpham;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Random;

public class QuanLySP {
    // Biến đếm toàn cục cho mã đơn hàng
    private static int soThuTuDonHang = 1;

    public static void chonQuanLySP(VBox vbox) {
        vbox.getChildren().clear();
        Button btnThemSP = new Button("Thêm Sản Phẩm");
        Button btnSuaSP = new Button("Sửa Sản Phẩm");
        Button btnXoaSP = new Button("Xóa Sản Phẩm");

        btnThemSP.setOnAction(event -> hienThiGDThemSP(vbox));

        vbox.getChildren().addAll(btnThemSP, btnSuaSP, btnXoaSP);
    }

    private static void hienThiGDThemSP(VBox vbox) {
        vbox.getChildren().clear();
        Label labelMaSP = new Label("Mã Sản Phẩm:");
        TextField txtMaSP = new TextField();
        Label labelTenSP = new Label("Tên Sản Phẩm:");
        TextField txtTenSP = new TextField();
        Label labelGiaSP = new Label("Giá Sản Phẩm:");
        TextField txtGiaSP = new TextField();
        Label labelSoLuongSP = new Label("Số Lượng:");
        TextField txtSoLuongSP = new TextField();
        Button btnLuu = new Button("Lưu");
        Button btnLuuDH = new Button("Lưu Đơn Hàng");

        // Nút Lưu Sản Phẩm
        btnLuu.setOnAction(event -> {
            String maSP = txtMaSP.getText();
            String tenSP = txtTenSP.getText();
            String giaSP = txtGiaSP.getText();
            String soLuong = txtSoLuongSP.getText();

            if (maSP.isEmpty() || tenSP.isEmpty() || giaSP.isEmpty() || soLuong.isEmpty()) {
                showAlert("Nhập không thành công");
            } else {
                LuuTruSP.luuThongTinSP(maSP, tenSP, giaSP, soLuong);
                showAlert("Nhập thành công!");
                
            }
        });

        // Nút Lưu Đơn Hàng
        btnLuuDH.setOnAction(event -> {
            vbox.getChildren().clear();

            // Thêm các trường nhập mã đơn hàng và mã khách hàng
            Label labelMaDH = new Label("Mã Đơn Hàng:");
            TextField txtMaDH = new TextField();
            Label labelMaKH = new Label("Mã Khách Hàng:");
            TextField txtMaKH =  new TextField();

            Button btnLuuDonHang = new Button("Lưu Đơn Hàng");

            btnLuuDonHang.setOnAction(e -> {
                String maDH = txtMaDH.getText();
                String maKH = txtMaKH.getText();
             
                if (maDH.isEmpty() || maKH.isEmpty()) {
                    showAlert("Vui lòng nhập đầy đủ mã đơn hàng và mã khách hàng");
                } else {
                    
	
					// Xử lý lưu đơn hàng với mã đơn hàng và mã khách hàng
                    LuuTru.luuThongTinDH(maDH, maKH);
                    showAlert("Lưu đơn hàng thành công!\nMã Đơn Hàng: " + maDH + "\nMã Khách Hàng: " + maKH);
                    vbox.getChildren().clear();
                }
            });

            vbox.getChildren().addAll(labelMaDH, txtMaDH, labelMaKH, txtMaKH, btnLuuDonHang);
        });

        vbox.getChildren().addAll(labelMaSP, txtMaSP, labelTenSP, txtTenSP, labelGiaSP, txtGiaSP, labelSoLuongSP, txtSoLuongSP, btnLuu, btnLuuDH);
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
