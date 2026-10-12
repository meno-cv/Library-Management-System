package Controllers.DashBoardButtons;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import Controllers.DashBoardButtons.Models.Book;
import Controllers.DashBoardButtons.Models.BookData;
import Controllers.DashBoardButtons.Models.Member;
import Controllers.DashBoardButtons.Models.MemberData;
import Controllers.DashBoardButtons.Models.Borrowing;
import Controllers.DashBoardButtons.Models.BorrowingData;

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

        cmbMember.setItems(FXCollections.observableArrayList());

        for (Member member : MemberData.memberList) {
            cmbMember.getItems().add(
                    member.getId() + " - " + member.getName()
            );
        }

        cmbBook.setItems(FXCollections.observableArrayList());

        for (Book book : BookData.bookList) {
            cmbBook.getItems().add(
                    book.getId() + " - " + book.getTitle()
            );
        }

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

        String memberID = cmbMember.getValue().split(" - ")[0];
        String bookID = cmbBook.getValue().split(" - ")[0];

        Book selectedBook = null;

        for (Book book : BookData.bookList) {
            if (book.getId().equalsIgnoreCase(bookID)) {
                selectedBook = book;
                break;
            }
        }

        if (selectedBook == null) {
            showAlert(Alert.AlertType.ERROR, "Book not found.");
            return;
        }

        if (selectedBook.getQuantity() <= 0) {
            showAlert(Alert.AlertType.ERROR,
                    "This book is currently unavailable.");
            return;
        }

        Borrowing borrowing = new Borrowing(
                memberID,
                bookID,
                dpIssueDate.getValue(),
                dpDueDate.getValue()
        );

        BorrowingData.borrowingList.add(borrowing);

        selectedBook.setQuantity(selectedBook.getQuantity() - 1);

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