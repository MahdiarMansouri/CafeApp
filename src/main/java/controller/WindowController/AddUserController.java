package controller.WindowController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import lombok.Setter;
import model.bl.PersonBL;
import model.bl.UserBL;
import model.entity.Person;
import model.entity.User;

import model.entity.enums.Gender;
import model.entity.enums.Role;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {
    @Setter
    private Stage stage;

    @FXML
    private TextField nameTxt, familyTxt, nationalIDTxt, phoneNumberTxt, usernameTxt, passwordTxt;

    @FXML
    private Button addUserBtn;

    @FXML
    private ComboBox<Gender> genderCmb;

    @FXML
    private ComboBox<Role> roleCmb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Gender value : Gender.values()) {
            genderCmb.getItems().add(value);
        }

        for (Role value : Role.values()) {
            roleCmb.getItems().add(value);
        }

        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }

        addUserBtn.setOnAction(event -> {
            UserBL userBL = new UserBL();
            PersonBL personBL = new PersonBL();
            try {
                User user = User.builder()
                        .username(usernameTxt.getText())
                        .password(passwordTxt.getText())
                        .role(roleCmb.getValue())
                        .build();
                Person person = Person.builder()
                        .name(nameTxt.getText())
                        .family(familyTxt.getText())
                        .gender(genderCmb.getValue())
                        .nationalId(nationalIDTxt.getText())
                        .phoneNumber(phoneNumberTxt.getText())
                        .build();

                userBL.save(user);
                personBL.save(person);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Added Successfully", ButtonType.OK);
                alert.showAndWait();
                resetForm();
            }catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }
        });
    }



    private void resetForm(){
        nameTxt.clear();
        familyTxt.clear();
        nationalIDTxt.clear();
        phoneNumberTxt.clear();
        usernameTxt.clear();
        passwordTxt.clear();
        genderCmb.setValue(Gender.MALE);
        roleCmb.setValue(Role.Cashier);
    }
}
