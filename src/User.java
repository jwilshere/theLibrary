import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class User {

    // Attribut "Type"
    // 3: Undergraduate, Limit= 3
    // 5: Postgraduate, Limit= 5
    // 8: PHD/candidate, Limit= 7
    // 10: Teacher/Professor, Limit= 10

    int Id;
    String Fnamn;
    String Lnamn;
    int PersonId;
    int Type;
    int ItemsBorrowed;
    int BorrowLimit;
    int Active;
    int Delays;
    Date SuspendDate;
    Book[] bookLista = new Book[10];

    public User(){
    }

    public User(int id, String fnamn, String lnamn, int personId, int type, int itemBorrowed, int borrowLimit, int active, int delays, Date suspendDate) {
        this.Id = id;
        this.Fnamn = fnamn;
        this.Lnamn = lnamn;
        this.PersonId = personId;
        this.Type = type;
        this.ItemsBorrowed = itemBorrowed;
        this.BorrowLimit = borrowLimit;
        this.Active = active;
        this.Delays = delays;
        this.SuspendDate = suspendDate;
    }

    public String getFnamn() {
        return Fnamn;
    }

    public void setFnamn(String fnamn) {
        Fnamn = fnamn;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getItemsBorrowed() {
        return ItemsBorrowed;
    }

    public void setItemsBorrowed(int itemsBorrowed) {
        ItemsBorrowed = itemsBorrowed;
    }

    public int getBorrowLimit() {
        return BorrowLimit;
    }

    public void setBorrowLimit(int borrowLimit) {
        BorrowLimit = borrowLimit;
    }

    public int isActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public int getDelays() {
        return Delays;
    }

    public void setDelays(int delays) {
        Delays = delays;
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    public Date getSuspendDate() {
        return SuspendDate;
    }

    public void setSuspendDate(Date suspendDate) {
        SuspendDate = suspendDate;
    }

    public Book[] getBookLista(){
        return bookLista;
    }

    public void addBook(Book newBook){
        if(ItemsBorrowed <= BorrowLimit){
           bookLista[ItemsBorrowed] = newBook;
        }
    }
}

