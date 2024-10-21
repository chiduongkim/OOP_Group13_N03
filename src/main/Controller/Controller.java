package snippet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {

    @SuppressWarnings("unused")
	private Stage stage;

    @FXML
    private Button btnQuanLySP;

    @FXML
    private Button btnQuanLyDH;

    @FXML
    public void initialize() {
        btnQuanLySP.setOnAction(e -> showProductManagement());
        btnQuanLyDH.setOnAction(e -> {
            try {
                showOrderManagement();
            } catch (Exception e1) {
                showError("Có lỗi xảy ra: " + e1.getMessage());
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void showProductManagement() {
        try {
            System.out.println("Mở màn hình Quản lý sản phẩm.");
        } catch (Exception e) {
            showError("Có lỗi xảy ra khi mở Quản lý sản phẩm: " + e.getMessage());
        }
    }

    private void showOrderManagement() {
        try {
            System.out.println("Mở màn hình Quản lý đơn hàng.");
        } catch (Exception e) {
            showError("Có lỗi xảy ra khi mở Quản lý đơn hàng: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
