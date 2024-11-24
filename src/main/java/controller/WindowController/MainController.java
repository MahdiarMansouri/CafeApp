package controller.WindowController;

import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Setter;
import model.bl.PersonBL;
import model.bl.UserBL;
import model.entity.Person;
import model.entity.User;
import model.entity.enums.Role;
import view.WindowsManager;


public class MainController implements Initializable {
    @Setter
    private Stage stage;

    @Setter
    private Person person;

    @FXML
    private Button menuBtn, addItemBtn, viewHistoryBtn, inventoryBtn, addUserBtn;

    @FXML
    private Label welcomeLbl, inventoryLbl, addUserLbl, addItemLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Role role = person.getUser().getRole();
            if (role == Role.Cashier) {
                hideAndDisableButton(addUserBtn, addUserLbl);
                hideAndDisableButton(addItemBtn, addItemLbl);
                hideAndDisableButton(inventoryBtn, inventoryLbl);
            } else if (role == Role.Storekeeper) {
                hideAndDisableButton(addUserBtn, addUserLbl);
            }
        });

        setupWindows(menuBtn, "menu");
        setupWindows(addItemBtn, "addItem");
        setupWindows(addUserBtn, "addUser");
        setupWindows(viewHistoryBtn, "viewHistory");
        setupWindows(inventoryBtn, "inventory");

    }

    public void setupWindows(Button button, String window) {
        button.setOnAction(event -> {
            try {
                switch (window) {
                    case "addItem":
                        WindowsManager.showAddItemWindow();
                        break;
                    case "addUser":
                        WindowsManager.showAddUserWindow();
                        break;
                    case "inventory":
                        WindowsManager.showInventoryWindow();
                        break;
                    case "menu":
                        WindowsManager.showMenuWindow();
                        break;
                    case "viewHistory":
                        WindowsManager.showHistoryWindow();
                        break;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }


    public void setUsername(String username) {
        try {
            PersonBL personBL = new PersonBL();
            Person person = personBL.findByUsername(username);
            welcomeLbl.setText("Welcome " + person.getName() + " " + person.getFamily());
            this.person = person;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Person Not Found\n" + e.getMessage());
            alert.show();
        }
    }

    public void hideAndDisableButton(Button button, Label label) {
        button.setVisible(false);
        label.setVisible(false);
        button.setDisable(true);
        label.setDisable(true);
    }
}
