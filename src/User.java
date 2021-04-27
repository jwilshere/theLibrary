public class User {

    int Id;
    String Type;
    int ItemBorrowed;
    int BorrowLimit;
    boolean Active;
    int Delays;

    public boolean canBorrow(){
        return false;
    }

    public void borrowBook(){}
}
