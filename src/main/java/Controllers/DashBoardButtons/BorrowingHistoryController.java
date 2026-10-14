package Controllers.DashBoardButtons;

import Controllers.DashBoardButtons.Models.Book;
import Controllers.DashBoardButtons.Models.BookData;
import Controllers.DashBoardButtons.Models.Borrowing;
import Controllers.DashBoardButtons.Models.BorrowingData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class BorrowingHistoryController {

    @FXML
    private TableColumn<Borrowing, String> colMemberID;

    @FXML
    private TableColumn<Borrowing, String> colBookTitle;

    @FXML
    private TableColumn<Borrowing, LocalDate> colIssueDate;

    @FXML
    private TableColumn<Borrowing, LocalDate> colDueDate;

    @FXML
    private TableColumn<Borrowing, LocalDate> colReturnDate;

    @FXML
    private TableColumn<Borrowing, String> colStatus;

    @FXML
    private TableView<Borrowing> tableMembers;

    @FXML
    private Button btnBack;

    private ObservableList<Borrowing> borrowingList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colMemberID.setCellValueFactory(
                new PropertyValueFactory<>("memberID"));

        colBookTitle.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        getBookTitle(cellData.getValue().getBookID())));

        colIssueDate.setCellValueFactory(
                new PropertyValueFactory<>("issueDate"));

        colDueDate.setCellValueFactory(
                new PropertyValueFactory<>("dueDate"));

        colReturnDate.setCellValueFactory(
                new PropertyValueFactory<>("returnDate"));

        colStatus.setCellValueFactory(
                new PropertyValueFactory<>("status"));


        borrowingList.setAll(BorrowingData.borrowingList);


        for (Borrowing borrowing : borrowingList) {

            if (borrowing.getReturnDate() == null
                    && LocalDate.now().isAfter(borrowing.getDueDate())) {

                borrowing.setStatus("Overdue");
            }
        }

        tableMembers.setItems(borrowingList);
    }

    private String getBookTitle(String bookID) {

        for (Book book : BookData.bookList) {

            if (book.getId().equals(bookID)) {
                return book.getTitle();
            }
        }

        return "Unknown Book";
    }

    @FXML
    private void btnBackOnAction(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/View/DashboadPage.fxml"));

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) btnBack.getScene().getWindow();

            stage.setScene(scene);

        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setHeaderText(null);
            alert.setContentText("Unable to return to the dashboard.");
            alert.showAndWait();
        }
    }
}