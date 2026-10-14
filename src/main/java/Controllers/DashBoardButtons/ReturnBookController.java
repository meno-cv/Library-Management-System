package Controllers.DashBoardButtons;

import Controllers.DashBoardButtons.Models.Book;
import Controllers.DashBoardButtons.Models.BookData;
import Controllers.DashBoardButtons.Models.Borrowing;
import Controllers.DashBoardButtons.Models.BorrowingData;
import Controllers.DashBoardButtons.Models.Member;
import Controllers.DashBoardButtons.Models.MemberData;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ReturnBookController {

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<Borrowing> cmbBorrowing;

    @FXML
    private DatePicker dpReturnDate;

    @FXML
    private Label lblMemberInformation;

    @FXML
    private Label lblBorrowedDate;

    @FXML
    private Label lblDueDate;

    @FXML
    private Label lblReturnDate;

    @FXML
    private Label lblOverdueStatus;

    @FXML
    private Label lblMemberValue;

    @FXML
    private Label lblBorrowedDateValue;

    @FXML
    private Label lblDueDateValue;

    @FXML
    private Label lblOverdueValue;

    @FXML
    public void initialize() {

        cmbBorrowing.setItems(
                FXCollections.observableArrayList()
        );

        for (Borrowing borrowing : BorrowingData.borrowingList) {

            if (borrowing.getReturnDate() == null) {
                cmbBorrowing.getItems().add(borrowing);
            }
        }

        cmbBorrowing.setConverter(
                new javafx.util.StringConverter<Borrowing>() {

                    @Override
                    public String toString(Borrowing borrowing) {

                        if (borrowing == null) {
                            return "";
                        }

                        Book book = findBook(borrowing.getBookID());

                        String title = book == null
                                ? borrowing.getBookID()
                                : book.getTitle();

                        return borrowing.getBookID() + " - " + title;
                    }

                    @Override
                    public Borrowing fromString(String string) {
                        return null;
                    }
                }
        );

        cmbBorrowing.setOnAction(event -> displayBorrowingDetails());

        dpReturnDate.setValue(LocalDate.now());

        displayBorrowingDetails();
    }


    private void displayBorrowingDetails() {

        Borrowing borrowing = cmbBorrowing.getValue();

        if (borrowing == null) {
            lblMemberValue.setText("-");
            lblBorrowedDateValue.setText("-");
            lblDueDateValue.setText("-");
            lblOverdueValue.setText("-");
            return;
        }

        Member member = findMember(borrowing.getMemberID());

        if (member != null) {
            lblMemberValue.setText(
                    member.getId() + " - " + member.getName()
            );
        } else {
            lblMemberValue.setText(borrowing.getMemberID());
        }

        lblBorrowedDateValue.setText(
                borrowing.getIssueDate().toString()
        );

        lblDueDateValue.setText(
                borrowing.getDueDate().toString()
        );

        LocalDate checkDate = dpReturnDate.getValue();

        if (checkDate == null) {
            checkDate = LocalDate.now();
        }

        if (checkDate.isAfter(borrowing.getDueDate())) {
            lblOverdueValue.setText("Overdue");
        } else {
            lblOverdueValue.setText("Not overdue");
        }
    }

    @FXML
    void btnReturnBookOnAction() {

        Borrowing borrowing = cmbBorrowing.getValue();

        if (borrowing == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Please select a borrowed book."
            );
            return;
        }

        if (dpReturnDate.getValue() == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Please select the return date."
            );
            return;
        }

        if (dpReturnDate.getValue().isBefore(borrowing.getIssueDate())) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Return date cannot be before the issue date."
            );
            return;
        }

        if (borrowing.getReturnDate() != null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "This book has already been returned."
            );
            return;
        }

        Book book = findBook(borrowing.getBookID());

        if (book == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Book information could not be found."
            );
            return;
        }

        LocalDate returnDate = dpReturnDate.getValue();

        borrowing.setReturnDate(returnDate);
        borrowing.setStatus(
                returnDate.isAfter(borrowing.getDueDate())
                        ? "Overdue"
                        : "Returned"
        );

        book.setQuantity(book.getQuantity() + 1);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Book returned successfully!"
        );

        cmbBorrowing.getItems().remove(borrowing);
        cmbBorrowing.getSelectionModel().clearSelection();

        dpReturnDate.setValue(LocalDate.now());

        displayBorrowingDetails();
    }

    private Book findBook(String bookID) {

        for (Book book : BookData.bookList) {
            if (book.getId().equalsIgnoreCase(bookID)) {
                return book;
            }
        }

        return null;
    }

    private Member findMember(String memberID) {

        for (Member member : MemberData.memberList) {
            if (member.getId().equalsIgnoreCase(memberID)) {
                return member;
            }
        }

        return null;
    }

    @FXML
    void btnBackOnAction() {

        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();

            stage.setScene(
                    new Scene(
                            FXMLLoader.load(
                                    getClass().getResource(
                                            "/View/DashboadPage.fxml"
                                    )
                            )
                    )
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void showAlert(Alert.AlertType type, String message) {

        Alert alert = new Alert(type);

        alert.setTitle(
                type == Alert.AlertType.ERROR ? "Error" : "Success"
        );

        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

