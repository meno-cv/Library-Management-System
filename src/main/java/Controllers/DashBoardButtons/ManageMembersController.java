package Controllers.DashBoardButtons;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableView<?> tableMembers;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {

        Stage stage = (Stage) btnAddMember.getScene().getWindow();

        try {
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/DashBoardButtons/AddMember.fxml"))
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}