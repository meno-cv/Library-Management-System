package Controllers.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Controllers.DashBoardButtons.Models.Book;
import Controllers.DashBoardButtons.Models.BookData;
import Controllers.DashBoardButtons.Models.Borrowing;
import Controllers.DashBoardButtons.Models.BorrowingData;
import Controllers.DashBoardButtons.Models.MemberData;

import java.time.LocalDate;


import java.io.IOException;

public class DashboardPageController {

    @FXML
    private Button btnBooks;

    @FXML
    private Button btnBorrowingHistory;

    @FXML
    private Button btnDashBoard;

    @FXML
    private Button btnIssueBook;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnMembers;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Label lblTotalBooks;

    @FXML
    private Label lblTotalMembers;

    @FXML
    private Label lblCurrentlyBorrowed;

    @FXML
    private Label lblOverdue;


    @FXML
    void btnBooksOnAction(ActionEvent event) {

        try {
            Stage stage = (Stage) btnBooks.getScene().getWindow();

            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/DashBoardButtons/AddBook.fxml"))
            ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnMembersOnAction(ActionEvent event) {

        Stage stage = (Stage) btnMembers.getScene().getWindow();

        try {
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/DashBoardButtons/ManageMembers.fxml"))
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnIssueBookOnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) btnIssueBook.getScene().getWindow();

            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/DashBoardButtons/IssueBook.fxml"))
            ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) btnReturnBook.getScene().getWindow();

            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/DashBoardButtons/ReturnBook.fxml"))
            ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBorrowingHistoryOnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) btnBorrowingHistory.getScene().getWindow();

            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/DashBoardButtons/BorrowingHistory.fxml"))
            ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {

        int totalBooks = 0;

        for (Book book : BookData.bookList) {
            totalBooks += book.getQuantity();
        }

        lblTotalBooks.setText(String.valueOf(totalBooks));

        lblTotalMembers.setText(
                String.valueOf(MemberData.memberList.size()));

        int currentlyBorrowed = 0;
        int overdue = 0;


        for (Borrowing borrowing : BorrowingData.borrowingList) {

            if (borrowing.getReturnDate() == null) {

                currentlyBorrowed++;

                if (LocalDate.now().isAfter(borrowing.getDueDate())) {
                    overdue++;
                }
            }
        }


        lblCurrentlyBorrowed.setText(
                String.valueOf(currentlyBorrowed));

        lblOverdue.setText(String.valueOf(overdue));
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {

        try {

            Stage stage = (Stage) btnLogOut.getScene().getWindow();

            stage.setScene(new Scene(
                    FXMLLoader.load(
                            getClass().getResource("/View/LoginPage.fxml"))
            ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}