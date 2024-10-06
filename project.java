import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JavaFXMySQLApp extends Application {

    private static final String URL = "jdbc:mysql://localhost:3306/projects"; 
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX với MySQL Demo");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Tên:");
        GridPane.setConstraints(nameLabel, 0, 0);

        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        Button addButton = new Button("Thêm");
        GridPane.setConstraints(addButton, 1, 1);

        grid.getChildren().addAll(nameLabel, nameInput, addButton);

        addButton.setOnAction(e -> {
            String name = nameInput.getText();
            if (!name.isEmpty()) {
                insertData(name);
                nameInput.clear();
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void insertData(String name) {
        String query = "INSERT INTO users (name) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Dữ liệu đã được thêm thành công!");

        } catch (SQLException e) {
            System.out.println("Lỗi khi kết nối đến MySQL: " + e.getMessage());
        }
    }
}
