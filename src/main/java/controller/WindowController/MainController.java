package controller.WindowController;

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
//        Role role = this.person.getUser().getRole();
//
//        if (role == Role.Cashier) {
//            hideAndDisableButton(addUserBtn, addUserLbl);
//            hideAndDisableButton(addItemBtn, addItemLbl);
//            hideAndDisableButton(inventoryBtn, inventoryLbl);
//        } else if (role == Role.Storekeeper) {
//            hideAndDisableButton(addUserBtn, addUserLbl);
//        }

        menuBtn.setOnAction(event -> {
            try {
                WindowsManager.showMenuWindow();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        addItemBtn.setOnAction(event -> {
            try {
                WindowsManager.showAddItemWindow();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });
        viewHistoryBtn.setOnAction(event -> {
        });
        inventoryBtn.setOnAction(event -> {
        });
        addUserBtn.setOnAction(event -> {
            try {
                WindowsManager.showAddUserWindow();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

    }


    public void setUsername(String username) throws Exception {
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

//    public void showAndEnableButton(Button button, Label label) {
//        button.setVisible(true);
//        label.setVisible(true);
//        button.setDisable(false);
//        label.setDisable(false);
//    }
}
