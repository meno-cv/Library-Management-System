package Controllers.DashBoardButtons;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.SpinnerValueFactory;

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
    public void initialize() {
        spnQuantity.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100)
        );
    }

    @FXML
    void btnAddBookOnAction() {

        if (txtBookID.getText().isEmpty() ||
                txtBookTitle.getText().isEmpty() ||
                txtAuthor.getText().isEmpty() ||
                txtCategory.getText().isEmpty() ||
                txtPublishedYear.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields.");
            alert.showAndWait();

        } else if (!txtPublishedYear.getText().matches("\\d{4}")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid published year.");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Book added successfully.");
            alert.showAndWait();
        }
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

}