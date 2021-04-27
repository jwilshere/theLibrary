public class User {

    // Attribut "Type" =
    // 1: Undergraduate
    // 2: Postgraduate
    // 3: PHD/candidate
    // 4: Teacher/Professor

    int Id;
    String Fnamn;
    String Lnamn;
    int PersonId;
    int Type;
    int ItemBorrowed;
    int BorrowLimit;
    boolean Active;
    int Delays;

    public User(){

    }

    public User(int id, String fnamn, String lnamn, int personId, int type, int itemBorrowed, int borrowLimit, boolean active, int delays) {
        this.Id = id;
        this.Fnamn = fnamn;
        this.Lnamn = lnamn;
        this.PersonId = personId;
        this.Type = type;
        this.ItemBorrowed = itemBorrowed;
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
        return ItemBorrowed;
    }

    public void setItemBorrowed(int itemBorrowed) {
        ItemBorrowed = itemBorrowed;
    }

    public int getBorrowLimit() {
        return BorrowLimit;
    }

    public void setBorrowLimit(int borrowLimit) {
        BorrowLimit = borrowLimit;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public int getDelays() {
        return Delays;
    }

    public void setDelays(int delays) {
        Delays = delays;
    }
}

