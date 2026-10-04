package Controllers.DashBoardButtons;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BorrowingHistoryController {

    @FXML
    private TableColumn<?, ?> colBookTitle;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colIssueDate;

    @FXML
    private TableColumn<?, ?> colMemberID;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableView<?> tableMembers;

}
