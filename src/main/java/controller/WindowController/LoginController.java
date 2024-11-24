package controller.WindowController;

import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Setter;
import model.bl.UserBL;
import view.WindowsManager;


public class LoginController implements Initializable {
    @Setter
    private Stage stage;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Clear Error\n" + e.getMessage());
            alert.show();
        }

        loginBtn.setOnAction(event -> {
            try {
                UserBL userBl = new UserBL();
                if (userBl.findByUsernameAndPassword(usernameTxt.getText(), passwordTxt.getText()) != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login Successful");
                    if (alert.showAndWait().get().equals(ButtonType.OK)) {
                        WindowsManager.showMainWindow(usernameTxt.getText());
                    }
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Login Failed\n " + e.getMessage());
                alert.show();

            }
        });
    }

    private void resetForm() throws Exception {
        try{
            usernameTxt.clear();
            passwordTxt.clear();
        }catch(Exception e){
            throw new Exception("Cant clear fields !!!");
        }
    }

}
