package application;

import donhang.QuanLyDH;
import sanpham.QuanLySP;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button btnQuanLyDH = new Button("Thông Tin Đơn Hàng");
        Button btnQuanLySP = new Button("Thông tin Sản Phẩm");

        VBox vboxMain = new VBox(20);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.getChildren().addAll(btnQuanLyDH, btnQuanLySP);
        btnQuanLyDH.setOnAction(event -> QuanLyDH.chonQuanLyDH(vboxMain));
        btnQuanLySP.setOnAction(event -> QuanLySP.chonQuanLySP(vboxMain));

        Scene scene = new Scene(vboxMain, 600, 400);
        primaryStage.setTitle("Quản Lý");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
  
}
