import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Getset {

    public void connectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Driver did not load");
        }
    }

    public User[] getUsers(){
        int index = 0;
        ArrayList<User> userLista = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {
            System.out.println("Connected");

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * FROM user");

            while(rs.next()){
                User anvandare = new User(rs.getInt("id"), rs.getString("Fnamn"), rs.getString("Lnamn"), rs.getInt("personid"), rs.getInt("type"), rs.getInt("itemBorrowed"), rs.getInt("borrowlimit"), rs.getInt("active"), rs.getInt("delays"), rs.getDate("SuspendDate"));
                userLista.add(anvandare);
                index++;
            }
        }
        catch(SQLException ex){
            System.out.println("Something went wrong");
        }
        User[] usLista = new User[index];
        userLista.toArray(usLista);
        return usLista;
    }

    public Book[] getBooks(){
        int index = 0;
        ArrayList<Book> bookLista = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {
            System.out.println("Connected");

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * FROM book");

            while(rs.next()){
                Book bok = new Book(rs.getInt("id"), rs.getString("title"), rs.getInt("ISBN"), rs.getDate("borrowedOnDate"));
                bookLista.add(bok);
                index++;
            }
        }
        catch(SQLException ex){
            System.out.println("Something went wrong");
        }
        Book[] BoLista = new Book[index];
        bookLista.toArray(BoLista);
        return BoLista;
    }

    public void deleteUser(int userId) {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root", "philip98")) {
            System.out.println("Connected");

            PreparedStatement deleteAUser = conn.prepareStatement("DELETE FROM admin.User WHERE id= " + "'" + userId + "'");
            deleteAUser.executeUpdate();

        }
        catch(SQLException ex){
            System.out.println("Something went wrong");
        }
        System.out.println("User deleted successfully!");
    }

    public void setUser(int id, String fnamn, String lnamn, int personId, int typ, int itemBorrowed, int borrowLimit, int active, int delays, Date suspendDate){

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {
            System.out.println("Connected");

            PreparedStatement userIn = conn.prepareStatement("INSERT INTO User VALUES (?,?,?,?,?,?,?,?,?,?)");
            userIn.setInt(1, id);
            userIn.setString(2, fnamn);
            userIn.setString(3, lnamn);
            userIn.setInt(4, personId);
            userIn.setInt(5, typ);
            userIn.setInt(6, itemBorrowed);
            userIn.setInt(7, borrowLimit);
            userIn.setInt(8, active);
            userIn.setInt(9, delays);
            userIn.setDate(10, suspendDate);

            userIn.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
    }

    public void setBook(int id, String title, int ISBN, Date borrowed){

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {
            System.out.println("Connected");

            PreparedStatement bookIn = conn.prepareStatement("INSERT INTO Book VALUES (?,?,?,?)");
            bookIn.setInt(1, id);
            bookIn.setString(2, title);
            bookIn.setInt(3, ISBN);
            bookIn.setDate(4, borrowed);

            bookIn.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
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

    public void updateItemBorrowed(int itemBorrowed, int userId){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {
            System.out.println("Connected");

            PreparedStatement bookIn = conn.prepareStatement("UPDATE User SET itemBorrowed = " + "'" + itemBorrowed + "'" + "WHERE user.id = " + "'" + userId + "'");
            bookIn.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
    }

    public void resetSuspend(int userId){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {
            System.out.println("Connected");

            PreparedStatement bookIn = conn.prepareStatement("UPDATE User SET itemBorrowed = " + "'" + itemBorrowed + "'" + "WHERE user.id = " + "'" + userId + "'");
            bookIn.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Getset hej = new Getset();

        hej.connectDB(); // FÖRSTA GÅNGEN MAN STARTAR BARA !!!!!!!!!!!!!!!!!

        System.out.println("Detta är användare: " + Arrays.toString(hej.getUsers()));
        System.out.println();
        System.out.println(Arrays.toString(hej.getBooks()));
        System.out.println();

        for(User s: hej.getUsers()) {
            if (s.getId() == 1234) {
                System.out.println(s.getId());
            }
        }
        System.out.println();


       // hej.setUser(5555, "Rasmus", "Johansson", 980428, 2, 2, 5, 1, 0);
        //hej.setBook(2200, "Moa's Inredningstips", 566756, null);
        System.out.println();

        for(User s: hej.getUsers()){
            System.out.println(s.getId());
        }
    }
}
