package sanpham;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class QuanLySP {
    public static void chonQuanLySP(VBox vbox) {
        vbox.getChildren().clear();
        Button btnThemSP = new Button("Thêm Sản Phẩm");
        Button btnSuaSP = new Button("Sửa Sản Phẩm");
        Button btnXoaSP = new Button("Xóa Sản Phẩm");
        btnThemSP.setOnAction(event -> hienThiGDThemSP(vbox)); 
        //btnSuaSP.setOnAction(event -> xuLySP("Sửa Sản Phẩm"));
        //btnXoaSP.setOnAction(event -> xuLySP("Xóa Sản Phẩm"));

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

        btnLuu.setOnAction(event -> {
            String maSP = txtMaSP.getText();
            String tenSP = txtTenSP.getText();
            String giaSP = txtGiaSP.getText();
            String soLuong = txtSoLuongSP.getText();

            if (maSP.isEmpty() || tenSP.isEmpty() || giaSP.isEmpty() || soLuong.isEmpty()) {
                showAlert("Nhập không thành công");
            } else {
                LuuTru.luuThongTinSP(maSP, tenSP, giaSP, soLuong);
                showAlert("Nhập thành công!");
                vbox.getChildren().clear(); 
            }
        });
        vbox.getChildren().addAll(labelMaSP, txtMaSP, labelTenSP, txtTenSP, labelGiaSP, txtGiaSP, labelSoLuongSP, txtSoLuongSP, btnLuu);
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
