import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class HelpMethod {
    Getset object;
    User anvandare;
    Book bok;

    public User getAUser(int UserId){
        object = new Getset();
        anvandare = new User();

        for(User s: object.getUsers()) {
            if (s.getId() == UserId) {
                anvandare = s;
            }
        }
        return anvandare;
    }

    public Book getABookOnId(int bookId){
        object = new Getset();
        bok = new Book();

        for(Book s: object.getBooks()) {
            if (s.getId() == bookId) {
                bok = s;
            }
        }
        return bok;
    }

    public void addBookToUser(int userId, int bookId){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {
            System.out.println("Connected");

            PreparedStatement bookIn = conn.prepareStatement("UPDATE Book SET User_id = " + "'" + userId + "'" + "WHERE book.id = " + "'" + bookId + "'");
            bookIn.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
    }

    public void removeBookFromUSer(int bookId){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {
            System.out.println("Connected");

            PreparedStatement bookIn = conn.prepareStatement("UPDATE Book SET User_id = null WHERE book.id = " + "'" + bookId + "'");
            bookIn.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
    }

    public void checkIfSuspended(int UserId){
        int delay;
        if(getAUser(UserId).isActive() == 1)
            getAUser(UserId).getDelays();


        else System.out.println("User is inactive");


        //if a member delays to return library items more than twice,
        // he/she gets suspended for 15 days.
        // If he/she has been suspended more than twice, then the account is deleted.
    }

    public static void main(String[] args) {
        HelpMethod hej = new HelpMethod();
        System.out.println(hej.getAUser(1234));
        System.out.println(hej.getABookOnId(987654));
        //hej.addBookToUser(1234, 4444);
        //hej.removeBookFromUSer(4444);
    }
}
