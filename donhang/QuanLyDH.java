package donhang;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class QuanLyDH {

    // Phương thức để hiển thị các tùy chọn quản lý đơn hàng
    public static void chonQuanLyDH(VBox vbox) {
        vbox.getChildren().clear();

        // Tạo các nút Thêm, Sửa và Xóa đơn hàng
        Button btnThemDH = new Button("Thêm Đơn Hàng");
        Button btnSuaDH = new Button("Sửa Đơn Hàng");
        Button btnXoaDH = new Button("Xóa Đơn Hàng");

        // Gán sự kiện cho nút Thêm Đơn Hàng
        btnThemDH.setOnAction(event -> hienThiGDThemDH(vbox));

        // Thêm các nút vào VBox
        vbox.getChildren().addAll(btnThemDH, btnSuaDH, btnXoaDH);
    }

    // Phương thức hiển thị giao diện thêm đơn hàng
    private static void hienThiGDThemDH(VBox vbox) {
        vbox.getChildren().clear();

        // Tạo label và textfield cho mã đơn hàng
        Label labelMaDH = new Label("Mã Đơn Hàng:");
        TextField txtMaDH = new TextField();

        // Tạo label và textfield cho mã khách hàng
        Label labelMaKH = new Label("Mã Khách Hàng:");
        TextField txtMaKH = new TextField();

        // Tạo nút Lưu
        Button btnLuu = new Button("Lưu");

        // Xử lý sự kiện khi nhấn nút Lưu
        btnLuu.setOnAction(event -> {
            String maDH = txtMaDH.getText();
            String maKH = txtMaKH.getText();

            // Kiểm tra nếu mã đơn hàng hoặc mã khách hàng bị bỏ trống
            if (maDH.isEmpty() || maKH.isEmpty()) {
                showAlert("Nhập không thành công, vui lòng điền đầy đủ thông tin.");
            } else {
                // Gọi phương thức để lưu thông tin đơn hàng
                LuuTru.luuThongTinDH(maDH, maKH);
                showAlert("Nhập thành công!");
                vbox.getChildren().clear(); // Xóa giao diện sau khi lưu thành công
            }
        });

        // Thêm các thành phần vào VBox
        vbox.getChildren().addAll(labelMaDH, txtMaDH, labelMaKH, txtMaKH, btnLuu);
    }

    // Phương thức hiển thị thông báo
    private static void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
