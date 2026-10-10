package Controllers.DashBoardButtons;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class IssueBookController {

    @FXML
    private Button btnIssueBook;

    @FXML
    private Label lblDueDate;

    @FXML
    private Label lblQuantity;

    @FXML
    private Label lblSelectBook;

    @FXML
    private Label lblSelectDate;

    @FXML
    private Label lblSelectMember;

    @FXML
    private ComboBox<String> cmbMember;

    @FXML
    private ComboBox<String> cmbBook;

    @FXML
    private DatePicker dpIssueDate;

    @FXML
    private DatePicker dpDueDate;

    @FXML
    public void initialize() {

        cmbMember.setItems(FXCollections.observableArrayList(
                "M001 - Alex",
                "M002 - Sarah",
                "M003 - David"
        ));

        cmbBook.setItems(FXCollections.observableArrayList(
                "B001 - Java Basics",
                "B002 - Database Systems",
                "B003 - Web Development"
        ));

        dpIssueDate.setValue(LocalDate.now());
    }

    @FXML
    void btnIssueBookOnAction(ActionEvent event) {

        if (cmbMember.getValue() == null ||
                cmbBook.getValue() == null ||
                dpIssueDate.getValue() == null ||
                dpDueDate.getValue() == null) {

            showAlert(Alert.AlertType.ERROR,
                    "Please complete all the fields.");

            return;
        }

        if (dpIssueDate.getValue().isBefore(LocalDate.now())) {

            showAlert(Alert.AlertType.ERROR,
                    "Issue date cannot be in the past.");

            return;
        }

        if (!dpDueDate.getValue().isAfter(dpIssueDate.getValue())) {

            showAlert(Alert.AlertType.ERROR,
                    "Due date must be after the issue date.");

            return;
        }

        showAlert(Alert.AlertType.INFORMATION,
                "Book issued successfully!");

        cmbMember.getSelectionModel().clearSelection();
        cmbBook.getSelectionModel().clearSelection();
        dpIssueDate.setValue(LocalDate.now());
        dpDueDate.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(type == Alert.AlertType.ERROR ? "Error" : "Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}