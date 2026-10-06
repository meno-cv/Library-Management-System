package Controllers.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
}