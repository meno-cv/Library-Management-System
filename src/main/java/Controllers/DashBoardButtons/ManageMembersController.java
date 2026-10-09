package Controllers.DashBoardButtons;

import Controllers.DashBoardButtons.Models.Member;
import Controllers.DashBoardButtons.Models.MemberData;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageMembersController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnAddMember;

    @FXML
    private TableColumn<Member, String> colEmail;

    @FXML
    private TableColumn<Member, String> colID;

    @FXML
    private TableColumn<Member, String> colName;

    @FXML
    private TableColumn<Member, String> colPhone;

    @FXML
    private TableView<Member> tableMembers;

    @FXML
    private TextField txtSearch;

    @FXML
    public void initialize() {

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        FilteredList<Member> filteredList =
                new FilteredList<>(MemberData.memberList, member -> true);

        tableMembers.setItems(filteredList);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredList.setPredicate(member -> {

                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }

                String search = newValue.toLowerCase().trim();

                return member.getId().toLowerCase().contains(search)
                        || member.getName().toLowerCase().contains(search);
            });
        });
    }

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {

        Stage stage = (Stage) btnAddMember.getScene().getWindow();

        try {
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource(
                            "/DashBoardButtons/AddMember.fxml"))
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        Member selectedMember = tableMembers.getSelectionModel()
                .getSelectedItem();

        if (selectedMember == null) {
            showAlert(Alert.AlertType.ERROR,
                    "Please select a member to delete.");
            return;
        }

        MemberData.memberList.remove(selectedMember);

        showAlert(Alert.AlertType.INFORMATION,
                "Member deleted successfully.");
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

        Member selectedMember = tableMembers.getSelectionModel()
                .getSelectedItem();

        if (selectedMember == null) {
            showAlert(Alert.AlertType.ERROR,
                    "Please select a member to edit.");
            return;
        }

        TextField nameField = new TextField(selectedMember.getName());
        TextField emailField = new TextField(selectedMember.getEmail());
        TextField phoneField = new TextField(selectedMember.getPhone());

        javafx.scene.layout.VBox box = new javafx.scene.layout.VBox(
                10,
                new javafx.scene.control.Label("Name:"),
                nameField,
                new javafx.scene.control.Label("Email:"),
                emailField,
                new javafx.scene.control.Label("Phone:"),
                phoneField
        );

        javafx.scene.control.Dialog<Void> dialog =
                new javafx.scene.control.Dialog<>();

        dialog.setTitle("Edit Member");
        dialog.setHeaderText("Update member details");

        javafx.scene.control.ButtonType saveButton =
                new javafx.scene.control.ButtonType(
                        "Save",
                        javafx.scene.control.ButtonBar.ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().addAll(
                saveButton,
                javafx.scene.control.ButtonType.CANCEL
        );

        box.setPadding(new javafx.geometry.Insets(10));
        dialog.getDialogPane().setContent(box);

        dialog.setResultConverter(button -> {

            if (button == saveButton) {

                if (nameField.getText().trim().isEmpty()
                        || emailField.getText().trim().isEmpty()
                        || phoneField.getText().trim().isEmpty()) {

                    showAlert(Alert.AlertType.ERROR,
                            "Please fill all the fields.");

                    return null;
                }

                selectedMember.setName(nameField.getText().trim());
                selectedMember.setEmail(emailField.getText().trim());
                selectedMember.setPhone(phoneField.getText().trim());

                tableMembers.refresh();

                showAlert(Alert.AlertType.INFORMATION,
                        "Member updated successfully.");
            }

            return null;
        });

        dialog.showAndWait();
    }

    private void showAlert(Alert.AlertType type, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(type == Alert.AlertType.ERROR ? "Error" : "Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}