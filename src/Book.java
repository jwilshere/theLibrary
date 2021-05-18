import java.util.Date;


public class Book {

    private int id;
    private String title;
    private int ISBN;
    private Date borrowed;
    private int userID;

    public Book(){}

    public Book(int id, String title, int ISBN, Date borrowed, int userID) {
        this.id = id;
        this.title = title;
        this.ISBN = ISBN;
        this.borrowed = borrowed;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public Date getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Date borrowed) {
        this.borrowed = borrowed;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
