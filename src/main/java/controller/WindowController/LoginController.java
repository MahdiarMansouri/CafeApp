package controller.WindowController;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Setter;
import view.WindowsManager;


public class LoginController implements Initializable {
    @Setter
    private Stage stage;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField usernameTxt, passwordTxt;



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
                WindowsManager.showPersonForm();
            } catch (Exception e) {
                System.out.println(e.getMessage());

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
