package Controllers.DashBoardButtons.Models;

public class Book {

    private String id;
    private String title;
    private String author;
    private String category;
    private String publishedYear;
    private int quantity;

    public Book(String id, String title, String author, String category, String publishedYear, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publishedYear = publishedYear;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
