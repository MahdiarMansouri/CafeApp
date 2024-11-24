package view;

import controller.WindowController.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowsManager {
    public static void showMainWindow(String username) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource("main.fxml"));
        Scene scene = new Scene(loader.load());

        MainController mainController = loader.getController();
        mainController.setUsername(username);


        stage.setScene(scene);
        stage.setTitle("Main Window");
        stage.show();
    }

    public static void showLoginWindow() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource("login.fxml"));
        Scene scene = new Scene(loader.load());

        LoginController loginController = loader.getController();

        loginController.setStage(stage);

        stage.setScene(scene);
        stage.setTitle("Login Window");
        stage.show();
    }
    public static void showAddItemWindow() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource("addItem.fxml"));
        Scene scene = new Scene(loader.load());

        AddItemController addItemController = loader.getController();

        addItemController.setStage(stage);

        stage.setScene(scene);
        stage.setTitle("Add Item Window");
        stage.show();
    }

    public static void showAddUserWindow() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource("addUser.fxml"));
        Scene scene = new Scene(loader.load());

        AddUserController addUserController = loader.getController();

        addUserController.setStage(stage);

        stage.setScene(scene);
        stage.setTitle("Add Item Window");
        stage.show();
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


    public static void showInventoryWindow () throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource("inventory.fxml"));
        Scene scene = new Scene(loader.load());

        InventoryController inventoryController = loader.getController();

        inventoryController.setStage(stage);

        stage.setScene(scene);
        stage.setTitle("Inventory Window");
        stage.show();
    }
    public static void showHistoryWindow () throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource("inventory.fxml"));
        Scene scene = new Scene(loader.load());

        InventoryController inventoryController = loader.getController();

        inventoryController.setStage(stage);

        stage.setScene(scene);
        stage.setTitle("History Window");
        stage.show();
    }

    public static void showMenuWindow () throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource("Menu.fxml"));
        Scene scene = new Scene(loader.load());

        MenuController menuController = loader.getController();

        menuController.setStage(stage);

        stage.setScene(scene);
        stage.setTitle("Menu Window");
        stage.show();
    }
//    public static void showWindowWithClose(Stage currentStage, String fxmlPath) throws IOException {
//        currentStage.close();
//
//        FXMLLoader loader = new FXMLLoader(WindowsManager.class.getResource(fxmlPath));
//        Scene scene = new Scene(loader.load());
//        Stage newStage = new Stage();
//        newStage.setScene(scene);
//        newStage.show();
//    }
}
