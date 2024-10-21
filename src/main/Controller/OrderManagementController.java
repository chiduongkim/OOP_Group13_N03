package snippet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OrderManagementController {

    @SuppressWarnings("unused")
	private Stage stage;

    @FXML
    private Button btnThemDonHang;

    @FXML
    private Button btnSuaDonHang;

    @FXML
    private Button btnXoaDonHang;

    @FXML
    private Button btnXemDanhSachDonHang;

    @FXML
    public void initialize() {
        btnThemDonHang.setOnAction(e -> {
            System.out.println("Thêm đơn hàng");
        });

        btnSuaDonHang.setOnAction(e -> {
            System.out.println("Sửa đơn hàng");
        });

        btnXoaDonHang.setOnAction(e -> {
            System.out.println("Xóa đơn hàng");
        });

        btnXemDanhSachDonHang.setOnAction(e -> {
            System.out.println("Xem danh sách đơn hàng");
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @SuppressWarnings("unused")
	private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
