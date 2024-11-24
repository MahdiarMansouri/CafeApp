package controller.WindowController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import lombok.Setter;
import model.bl.ItemBL;
import model.entity.Item;
import model.entity.enums.Category;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {
    @Setter
    private Stage stage;

    @FXML
    private TextField itemNameTxt, descriptionTxt, priceTxt;

    @FXML
    private Button addItemBtn;

    @FXML
    private CheckBox availableChk;

    @FXML
    private ComboBox<Category> categoryCmb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Category value : Category.values()) {
            categoryCmb.getItems().add(value);
        }
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }

        addItemBtn.setOnAction(event -> {
            ItemBL itemBL = new ItemBL();
            Item item = Item
                    .builder()
                    .name(itemNameTxt.getText())
                    .description(descriptionTxt.getText())
                    .category(categoryCmb.getValue())
                    .price(Integer.parseInt(priceTxt.getText()))
                    .build();

            try {
                itemBL.save(item);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item Saved");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }

            resetForm();

        });
    }



    private void resetForm(){
        itemNameTxt.clear();
        descriptionTxt.clear();
        priceTxt.clear();
        categoryCmb.setValue(Category.Coffee);
        availableChk.setSelected(true);

    }
}
