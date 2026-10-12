package Controllers.DashBoardButtons.Models;

import java.time.LocalDate;

public class Borrowing {

    private String memberID;
    private String bookID;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;

    public Borrowing(String memberID, String bookID, LocalDate issueDate, LocalDate dueDate) {
        this.memberID = memberID;
        this.bookID = bookID;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.status = "Borrowed";
    }

    public String getMemberID() {
        return memberID;
    }

    public String getBookID() {
        return bookID;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
