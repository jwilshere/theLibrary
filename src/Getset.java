import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Getset {
    private static Logger logger = LogManager.getLogger(Getset.class.getName());

    public void connectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Driver did not load");
        }
    }

    public ArrayList<User> getUsers() throws SQLException{
        ArrayList<User> userLista = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * FROM user");

            while(rs.next()){
                User anvandare = new User(rs.getInt("id"), rs.getString("Fnamn"), rs.getString("Lnamn"), rs.getInt("personid"), rs.getInt("type"), rs.getInt("itemBorrowed"), rs.getInt("borrowlimit"), rs.getInt("active"), rs.getInt("delays"), rs.getDate("SuspendDate"));
                userLista.add(anvandare);
            }
        }
        catch(SQLException ex){
            System.out.println("Something went wrong");
        }
        return userLista;
    }

    public ArrayList<Book> getBooks() throws SQLException{
        ArrayList<Book> bookLista = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * FROM book");

            while(rs.next()){
                Book bok = new Book(rs.getInt("id"), rs.getString("title"), rs.getInt("ISBN"), rs.getDate("borrowedOnDate"));
                bookLista.add(bok);
            }
        }
        catch(SQLException ex){
            System.out.println("Something went wrong");
        }
        return bookLista;
    }

    public void deleteUser(int userId) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root", "philip98")) {

            PreparedStatement deleteAUser = conn.prepareStatement("DELETE FROM User WHERE id= " + "'" + userId + "'");
            deleteAUser.executeUpdate();

        }
        catch(SQLException ex){
            System.out.println("Something went wrong");
        }
        System.out.println("User deleted successfully!");
    }

    public void addUser(int id, String fnamn, String lnamn, int personId, int typ, int itemBorrowed, int borrowLimit, int active, int delays, Date suspendDate) throws SQLException{

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {

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

    public void addBook(int id, String title, int ISBN){
        logger.trace("---> addBok");

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {

            PreparedStatement bookIn = conn.prepareStatement("INSERT INTO Book VALUES (?,?,?,null,null)");
            bookIn.setInt(1, id);
            bookIn.setString(2, title);
            bookIn.setInt(3, ISBN);

            bookIn.executeUpdate();
            System.out.println("Bok med ID: " + id + " är tillagd!");
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
            logger.error("Ingen bok lades till, metodfel!");
        }
        logger.trace("<--- addBok");
    }

    public void addBookToUser(int userId, int bookId, int itemBorrowed, Date datum ) throws SQLException{
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {

            PreparedStatement bookIn = conn.prepareStatement("UPDATE Book SET User_id = " + "'" + userId + "'" + "WHERE book.id = " + "'" + bookId + "'");
            bookIn.executeUpdate();

            PreparedStatement itemsborrowedIn = conn.prepareStatement("UPDATE user SET itemBorrowed = " + "'" + itemBorrowed + "'" + "WHERE user.id = " + "'" + userId + "'");
            itemsborrowedIn.executeUpdate();

            PreparedStatement borrowedDateIn = conn.prepareStatement("UPDATE Book SET BorrowedOnDate = " + "'" + datum + "'" + "WHERE book.id = " + "'" + bookId + "'");
            borrowedDateIn.executeUpdate();

        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
    }

    public void removeBookFromUSer(int bookId, int userId) throws SQLException{
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {

            PreparedStatement bookIn = conn.prepareStatement("UPDATE Book SET User_id = null WHERE book.id = " + "'" + bookId + "'");
            bookIn.executeUpdate();

            PreparedStatement bookDateIn = conn.prepareStatement("UPDATE Book SET BorrowedOnDate = null WHERE book.id = " + "'" + bookId + "'");
            bookDateIn.executeUpdate();

            int itemsBorrowed = getItemsborrowed(userId) - 1;

            PreparedStatement itemsBorrowedIn = conn.prepareStatement("UPDATE user SET ItemBorrowed = " + "'" + itemsBorrowed + "'" + "WHERE user.id = " + "'" + userId + "'");
            itemsBorrowedIn.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
    }

    public int getItemsborrowed(int userId){
        int itemsBorrowed = 0;
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT user.ItemBorrowed from user WHERE user.Id = " + "'" + userId + "'");

            while(rs.next()){
                itemsBorrowed = rs.getInt("ItemBorrowed");
            }
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
        return itemsBorrowed;
    }

    public void deleteBook(int id){
        logger.trace("---> deleteBok");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root", "philip98")) {

            PreparedStatement deleteBok = conn.prepareStatement("DELETE FROM Book WHERE id= " + "'" + id + "'");
            deleteBok.executeUpdate();

        }
        catch(SQLException ex){
            System.out.println("Something went wrong");
            logger.error("Ingen bok raderades, metodfel!");
        }
        System.out.println("Bok raderad!");
        logger.trace("<--- deleteBok");
    }

   public Book[] getBooksBorrowedByUser(int userId) throws SQLException{
        Book[] booksLendedByUser = new Book[10];
        int nmr = 0;

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from book WHERE book.User_id = " + "'" + userId + "'");

            while(rs.next()){
                Book bok = new Book(rs.getInt("id"), rs.getString("title"), rs.getInt("ISBN"), rs.getDate("borrowedOnDate"));
                booksLendedByUser[nmr] = bok;
                nmr++;
            }
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
        return booksLendedByUser;
    }

    public void resetSuspend(int userId) throws SQLException{
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {

            PreparedStatement bookIn = conn.prepareStatement("UPDATE User SET suspendDate = null  WHERE User.id = " + "'" + userId + "'");
            bookIn.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
    }

    public void suspendUser(int userId, Date suspendDate) throws SQLException{
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {

            PreparedStatement bookIn = conn.prepareStatement("UPDATE User SET suspendDate = " + "'" + suspendDate + "'" +  "WHERE User.id = " + "'" + userId + "'");
            bookIn.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
    }
    public void setDelays(int userId, int delays) throws SQLException{
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {

            PreparedStatement bookIn = conn.prepareStatement("UPDATE User SET delays = " + "'" + delays + "'" +  "WHERE User.id = " + "'" + userId + "'");
            bookIn.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException{
        Getset hej = new Getset();
        hej.connectDB(); // FÖRSTA GÅNGEN MAN STARTAR BARA !!!!!!!!!!!!!!!!!

        hej.removeBookFromUSer(1234,1234);


    }
}
