package Controllers.DashBoardButtons;

import Controllers.DashBoardButtons.Models.Member;
import Controllers.DashBoardButtons.Models.MemberData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

        String id = txtMemberID.getText().trim();
        String name = txtFullName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhoneNumber.getText().trim();
        String address = txtAddress.getText().trim();

        if (id.isEmpty() || name.isEmpty() || email.isEmpty()
                || phone.isEmpty() || address.isEmpty()) {

            showAlert(Alert.AlertType.ERROR,
                    "Please fill all the fields.");
            return;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {

            showAlert(Alert.AlertType.ERROR,
                    "Please enter a valid email address.");
            return;
        }

        for (Member member : MemberData.memberList) {

            if (member.getId().equalsIgnoreCase(id)) {
                showAlert(Alert.AlertType.ERROR,
                        "This Member ID already exists.");
                return;
            }
        }

        Member member = new Member(id, name, email, phone);

        MemberData.memberList.add(member);

        showAlert(Alert.AlertType.INFORMATION,
                "Member registered successfully.");

        openManageMembers();
    }

    private void openManageMembers() {

        Stage stage = (Stage) btnRegisterMember.getScene().getWindow();

        try {
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource(
                            "/DashBoardButtons/ManageMembers.fxml"))
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(Alert.AlertType type, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(type == Alert.AlertType.ERROR ? "Error" : "Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}