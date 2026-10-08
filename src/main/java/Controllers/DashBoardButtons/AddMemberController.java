package Controllers.DashBoardButtons;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddMemberController {

    @FXML
    private Button btnRegisterMember;

    @FXML
    private TextField txtMemberID;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextArea txtAddress;

    @FXML
    void btnRegisterMemberOnAction(ActionEvent event) {

        if (txtMemberID.getText().trim().isEmpty() ||
                txtFullName.getText().trim().isEmpty() ||
                txtEmail.getText().trim().isEmpty() ||
                txtPhoneNumber.getText().trim().isEmpty() ||
                txtAddress.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields.");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Member registered successfully.");
            alert.showAndWait();
        }
    }



}
