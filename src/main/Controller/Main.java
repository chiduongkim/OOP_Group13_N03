package snippet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML layout for the main interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("QuanLyDH.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            
            primaryStage.setTitle("Quản lý bán hàng");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Optionally, pass the stage to the controller if needed
            OrderManagementController controller = loader.getController();
            controller.setStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
