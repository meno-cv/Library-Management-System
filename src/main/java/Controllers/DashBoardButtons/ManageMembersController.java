package Controllers.DashBoardButtons;

import Controllers.DashBoardButtons.Models.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    private ObservableList<Member> memberList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableMembers.setItems(memberList);
    }

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
