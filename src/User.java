public class User {

    int Id;
    int Type;
    int ItemBorrowed;
    int BorrowLimit;
    boolean Active;
    int Delays;

    public User(){}

    public User(int id, int type, int itemBorrowed, int borrowLimit, boolean active, int delays) {
        Id = id;
        Type = type;
        ItemBorrowed = itemBorrowed;
        BorrowLimit = borrowLimit;
        Active = active;
        Delays = delays;
    }

    public boolean canBorrow(int id){



        return false;
    }

    public void borrowBook(){}


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

