import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Getset {

    ArrayList<User> userLista = null;
    ArrayList<Book> bookLista = null;

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
        connectDB();
        userLista = new ArrayList<>();
        int index = 0;

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {
            System.out.println("Connected");

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * FROM user");

            while(rs.next()){
                User anvandare = new User(rs.getInt("id"), rs.getString("Fnamn"), rs.getString("Lnamn"), rs.getInt("personid"), rs.getInt("type"), rs.getInt("itemBorrowed"), rs.getInt("borrowlimit"), rs.getInt("active"), rs.getInt("delays"));
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
        connectDB();
        bookLista = new ArrayList<>();
        int index = 0;

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
        Book[] buLista = new Book[index];
        bookLista.toArray(buLista);
        return buLista;
    }


    /* public String getFnamn(int id){
        connectDB();
        String Fnamn = "";

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {

            System.out.println("Connected");

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery("Select Fnamn FROM user WHERE Id =" + "'" + id + "'");

            while(result.next()){
            Fnamn= result.getString(1);
            }
        }
        catch(SQLException ex){
            System.out.println("Something went wrong");
        }
        return Fnamn;
    } */

    public void setUser(int id, String fnamn, String lnamn, int personId, int type, int itemBorrowed, int borrowLimit, int active, int delays){

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {
            System.out.println("Connected");

            PreparedStatement userIn = conn.prepareStatement("INSERT INTO User VALUES (?,?,?,?,?,?,?,?,?)");
            userIn.setInt(1, id);
            userIn.setString(2, fnamn);
            userIn.setString(3, lnamn);
            userIn.setInt(4, personId);
            userIn.setInt(5, type);
            userIn.setInt(6, itemBorrowed);
            userIn.setInt(7, borrowLimit);
            userIn.setInt(8, active);
            userIn.setInt(9, delays);

            userIn.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Getset hej = new Getset();

        System.out.println(Arrays.toString(hej.getUsers()));
        System.out.println();
        System.out.println(Arrays.toString(hej.getBooks()));
        System.out.println();

        for(User s: hej.getUsers()){
            System.out.println(s.getId());
        }

        System.out.println();

        hej.setUser(5555, "Rasmus", "Johansson", 980428, 2, 2, 5, 1, 0);

        for(User s: hej.getUsers()){
            System.out.println(s.getId());
        }



    }
}
