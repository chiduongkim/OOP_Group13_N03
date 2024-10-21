package com.example.finalproj;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Giao diện chính
        Button btnQuanLySP = new Button("Quản lý sản phẩm");
        Button btnQuanLyDH = new Button("Quản lý đơn hàng");

        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(btnQuanLySP, btnQuanLyDH);

        Scene mainScene = new Scene(mainLayout, 400, 400);

        // Giao diện quản lý sản phẩm
        Button btnThemSP = new Button("Thêm sản phẩm");
        Button btnSuaSP = new Button("Sửa sản phẩm");
        Button btnXoaSP = new Button("Xóa sản phẩm");
        Button btnXemDanhSachSP = new Button("Xem danh sách sản phẩm");

        VBox spLayout = new VBox(20);
        spLayout.setAlignment(Pos.CENTER);
        spLayout.getChildren().addAll(btnThemSP, btnSuaSP, btnXoaSP, btnXemDanhSachSP);

        Scene spScene = new Scene(spLayout, 400, 400);

        // Giao diện quản lý đơn hàng
        Button btnThemDH = new Button("Thêm đơn hàng");
        Button btnSuaDH = new Button("Sửa đơn hàng");
        Button btnXoaDH = new Button("Xóa đơn hàng");
        Button btnXemDanhSachDH = new Button("Xem danh sách đơn hàng");

        VBox dhLayout = new VBox(20);
        dhLayout.setAlignment(Pos.CENTER);
        boolean b = dhLayout.getChildren().addAll(btnThemDH, btnSuaDH, btnXoaDH, btnXemDanhSachDH);

        Scene dhScene = new Scene(dhLayout, 400, 400);

        // Xử lí các sự kiện
        btnQuanLySP.setOnAction(e -> primaryStage.setScene(spScene));
        btnQuanLyDH.setOnAction(e -> primaryStage.setScene(dhScene));
        btnThemSP.setOnAction(e -> showProductForm(primaryStage, "Thêm sản phẩm", true));
        btnSuaSP.setOnAction(e -> showProductForm(primaryStage, "Sửa sản phẩm", false));
        btnXoaSP.setOnAction(e -> showDeleteProductForm(primaryStage));
        btnXemDanhSachSP.setOnAction(e -> {
            try {
                showProductList(primaryStage);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        btnThemDH.setOnAction(e -> showOrderForm(primaryStage, "Thêm đơn hàng", true));
        btnSuaDH.setOnAction(e -> showOrderForm(primaryStage, "Sửa đơn hàng", false));
        btnXoaDH.setOnAction(e -> showDeleteOrderForm(primaryStage));
        btnXemDanhSachDH.setOnAction(e -> showOrderList(primaryStage));
        btnXemDanhSachSP.setOnAction(e -> {
            try {
                showProductList(primaryStage);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        // Hiển thị giao diện chính ban đầu
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Quản lý bán hàng");
        primaryStage.show();
    }

    // quản lý sản phẩm (Thêm hoặc Sửa)
    private void showProductForm(Stage stage, String title, boolean isAdding) {
        TextField maSPField = new TextField();
        maSPField.setPromptText("Mã sản phẩm");
        TextField tenSPField = new TextField();
        tenSPField.setPromptText("Tên sản phẩm");
        TextField giaSPField = new TextField();
        giaSPField.setPromptText("Giá sản phẩm");
        TextField soLuongField = new TextField();
        soLuongField.setPromptText("Số lượng");

        Button submitButton = new Button(isAdding ? "Thêm sản phẩm" : "Sửa sản phẩm");
        submitButton.setOnAction(e -> {
            try {
                String maSP = maSPField.getText();
                String tenSP = tenSPField.getText();
                double giaSP = Double.parseDouble(giaSPField.getText());
                int soLuong = Integer.parseInt(soLuongField.getText());

                if (maSP.isEmpty() || tenSP.isEmpty()) {
                    throw new IllegalArgumentException("Các trường không được để trống");
                }

                if (isAdding) {
                    QuanLySP.themSanPham(maSP, tenSP, giaSP, soLuong);
                } else {
                    QuanLySP.capNhatSanPham(maSP, tenSP, giaSP, soLuong);
                }

                stage.setScene(stage.getScene());
            } catch (NumberFormatException ex) {
                showError("Giá và số lượng phải là số.");
            } catch (IllegalArgumentException ex) {
                showError(ex.getMessage());
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        VBox layout = new VBox(10, maSPField, tenSPField, giaSPField, soLuongField, submitButton);
        layout.setAlignment(Pos.CENTER);
        Scene formScene = new Scene(layout, 400, 400);

        stage.setTitle(title);
        stage.setScene(formScene);
    }

    // xóa sản phẩm
    private void showDeleteProductForm(Stage stage) {
        TextField maSPField = new TextField();
        maSPField.setPromptText("Mã sản phẩm");

        Button xoaSPButton = new Button("Xóa sản phẩm");
        xoaSPButton.setOnAction(e -> {
            try {
                String maSP = maSPField.getText();
                if (maSP.isEmpty()) {
                    throw new IllegalArgumentException("Mã sản phẩm không được để trống");
                }

                QuanLySP.xoaSanPham(maSP);
                stage.setScene(stage.getScene());
            } catch (IllegalArgumentException | ClassNotFoundException ex) {
                showError(ex.getMessage());
            }
        });

        VBox layout = new VBox(10, maSPField, xoaSPButton);
        layout.setAlignment(Pos.CENTER);
        Scene deleteScene = new Scene(layout, 400, 400);

        stage.setTitle("Xóa sản phẩm");
        stage.setScene(deleteScene);
    }
    //  hiển thị danh sách sản phẩm
    private void showProductList(Stage stage) throws ClassNotFoundException {
        ListView<String> productListView = new ListView<>();
        List<String> danhSachSanPham = QuanLySP.layDanhSachSanPham();\
        productListView.getItems().addAll(danhSachSanPham);

        VBox layout = new VBox(10, new Label("Danh sách sản phẩm"), productListView);
        layout.setAlignment(Pos.CENTER);
        Scene listScene = new Scene(layout, 400, 400);

        stage.setScene(listScene);
        stage.setTitle("Danh sách sản phẩm");
    }

    // quản lý đơn hàng (Thêm hoặc Sửa)
    private void showOrderForm(Stage stage, String title, boolean isAdding) {
        TextField maDHField = new TextField();
        maDHField.setPromptText("Mã đơn hàng");
        TextField tenKHField = new TextField();
        tenKHField.setPromptText("Tên khách hàng");
        DatePicker ngayTaoPicker = new DatePicker(LocalDate.now());

        Button btnSubmit = new Button(isAdding ? "Thêm" : "Sửa");
        btnSubmit.setOnAction(e -> {
            String maDH = maDHField.getText();
            String tenKH = tenKHField.getText();
            Date ngayTao = Date.valueOf(ngayTaoPicker.getValue());

            try {
                if (isAdding) {
                    QuanLyDH.themDonHang(maDH, tenKH, ngayTao);
                    showAddProductToOrderForm(stage, maDH);  // Mở form thêm sản phẩm
                } else {
                    QuanLyDH.capNhatDonHang(maDH, tenKH);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Cập nhật đơn hàng thành công!");
                    alert.showAndWait();
                }
            } catch (Exception ex) {
                showError("Có lỗi xảy ra: " + ex.getMessage());
            }
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(new Label("Mã đơn hàng:"), maDHField, new Label("Tên khách hàng:"), tenKHField, new Label("Ngày tạo:"), ngayTaoPicker, btnSubmit);

        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.show();
    }
    // thêm sản phẩm vào đơn hàng
    private void showAddProductToOrderForm(Stage stage, String maDH) {
        TextField maSPField = new TextField();
        maSPField.setPromptText("Mã sản phẩm");
        TextField soLuongField = new TextField();
        soLuongField.setPromptText("Số lượng");

        Button btnSubmit = new Button("Thêm sản phẩm vào đơn hàng");
        btnSubmit.setOnAction(e -> {
            String maSP = maSPField.getText();
            int soLuong = Integer.parseInt(soLuongField.getText());
            try {
                QuanLyDH.themSanPhamVaoDonHang(maDH, maSP, soLuong);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Thêm sản phẩm thành công!");
                alert.showAndWait();
            } catch (Exception ex) {
                showError("Có lỗi xảy ra: " + ex.getMessage());
            }
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(new Label("Mã sản phẩm:"), maSPField, new Label("Số lượng:"), soLuongField, btnSubmit);

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    // xóa đơn hàng
    private void showDeleteOrderForm(Stage stage) {
        TextField maDHField = new TextField();
        maDHField.setPromptText("Mã đơn hàng");

        Button xoaDHButton = new Button("Xóa đơn hàng");
        xoaDHButton.setOnAction(e -> {
            try {
                String maDH = maDHField.getText();
                if (maDH.isEmpty()) {
                    throw new IllegalArgumentException("Mã đơn hàng không được để trống");
                }

                QuanLyDH.xoaDonHang(maDH);
                stage.setScene(stage.getScene());
            } catch (IllegalArgumentException ex) {
                showError(ex.getMessage());
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        VBox layout = new VBox(10, maDHField, xoaDHButton);
        layout.setAlignment(Pos.CENTER);
        Scene deleteScene = new Scene(layout, 400, 400);

        stage.setTitle("Xóa đơn hàng");
        stage.setScene(deleteScene);
    }
    private void showOrderList(Stage stage) {
        List<String> danhSachDonHang;
        try {
            danhSachDonHang = QuanLyDH.layDanhSachDonHang();
        } catch (Exception ex) {
            showError("Có lỗi xảy ra: " + ex.getMessage());
            return;
        }

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        for (String donHang : danhSachDonHang) {
            Label label = new Label(donHang);
            layout.getChildren().add(label);
        }

        Button btnBack = new Button("Quay lại");
        btnBack.setOnAction(e -> stage.setScene(new Scene(layout, 400, 400)));
        layout.getChildren().add(btnBack);

        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
