package view;

import controller.WindowController.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowsManager {
    public static void showPersonForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("view/profile.fxml"))
        );

        stage.setScene(scene);
        stage.setTitle("Profile");
        stage.show();
    }

    public static void showLoginWindow() throws IOException {
        Stage stage = new Stage();
        System.out.println("here");
        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource("login.fxml"));

//        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource("src/main/view/login.fxml"));
        System.out.println("here");
        Scene scene = new Scene(loader.load());
        System.out.println("here");

        LoginController loginController = loader.getController();
        System.out.println("here");

        loginController.setStage(stage);
        System.out.println("here");

        stage.setScene(scene);
        stage.setTitle("Login");
//        stage.show();
    }

    public static void showAboutForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("view/about.fxml"))
        );


        stage.setScene(scene);
        stage.setTitle("About");
        stage.show();
    }
}
