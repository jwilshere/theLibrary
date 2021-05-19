import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    Book[] bookLista = new Book[10];

    public User(){
    }

    public User(int id, String fnamn, String lnamn, int personId, int type, int itemBorrowed, int borrowLimit, int active, int delays) {
        this.Id = id;
        this.Fnamn = fnamn;
        this.Lnamn = lnamn;
        this.PersonId = personId;
        this.Type = type;
        this.ItemsBorrowed = itemBorrowed;
        this.BorrowLimit = borrowLimit;
        this.Active = active;
        this.Delays = delays;
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

    public int getItemBorrowed() {
        return ItemsBorrowed;
    }

    public void setItemBorrowed(int itemBorrowed) {
        ItemsBorrowed = itemBorrowed;
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

    public Book[] getBookLista(){
        return bookLista;
    }
}

