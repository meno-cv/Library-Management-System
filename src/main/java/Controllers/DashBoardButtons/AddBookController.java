package Controllers.DashBoardButtons;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.SpinnerValueFactory;
import Controllers.DashBoardButtons.Models.Book;
import Controllers.DashBoardButtons.Models.BookData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AddBookController {

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblBookID;

    @FXML
    private Label lblBookTitle;

    @FXML
    private Label lblCategory;

    @FXML
    private Label lblPublishedYear;

    @FXML
    private Label lblQuantity;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtBookTitle;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtPublishedYear;

    @FXML
    private Spinner<Integer> spnQuantity;

    @FXML
    private Button btnBack;


    @FXML
    public void initialize() {
        spnQuantity.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100)
        );
    }

    @FXML
    void btnAddBookOnAction() {

        String id = txtBookID.getText().trim();
        String title = txtBookTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String category = txtCategory.getText().trim();
        String year = txtPublishedYear.getText().trim();
        int quantity = spnQuantity.getValue();

        if (id.isEmpty() || title.isEmpty() || author.isEmpty()
                || category.isEmpty() || year.isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Please fill all the fields.");
            return;
        }

        if (!year.matches("\\d{4}")) {
            showAlert(Alert.AlertType.ERROR, "Please enter a valid published year.");
            return;
        }

        for (Book book : BookData.bookList) {
            if (book.getId().equalsIgnoreCase(id)) {
                showAlert(Alert.AlertType.ERROR, "This Book ID already exists.");
                return;
            }
        }

        Book book = new Book(id, title, author, category, year, quantity);
        BookData.bookList.add(book);

        showAlert(Alert.AlertType.INFORMATION, "Book added successfully.");

        btnClearOnAction();

    }


    @FXML
    void btnClearOnAction() {

        txtBookID.clear();
        txtBookTitle.clear();
        txtAuthor.clear();
        txtCategory.clear();
        txtPublishedYear.clear();

        spnQuantity.getValueFactory().setValue(1);
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(type == Alert.AlertType.ERROR ? "Error" : "Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnBackOnAction() {

        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();

            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/View/DashboadPage.fxml"))
            ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}