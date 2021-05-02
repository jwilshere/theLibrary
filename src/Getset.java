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
        connectDB();
        ArrayList<User> userLista = new ArrayList<>();
        int index = 0;

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin?serverTimezone=UTC",
                "root","philip98")) {
            System.out.println("Connected");

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * FROM user");

            while(rs.next()){
                User anvandare = new User(rs.getInt("id"), rs.getString("Fnamn"), rs.getString("Lnamn"), rs.getInt("personid"), rs.getInt("type"), rs.getInt("itemBorrowed"), rs.getInt("borrowlimit"), rs.getInt("active"), rs.getInt("delays"));
                userLista.add(anvandare);
            }
        }
        catch(SQLException ex){
            System.out.println("Something went wrong");
        }
        User[] usLista = new User[10];
        userLista.toArray(usLista);
        return usLista;
    }

    public String getFnamn(int id){
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
    }

    public static void main(String[] args) {
        Getset hej = new Getset();

        System.out.println(Arrays.toString(hej.getUsers()));
        System.out.println();
        System.out.println(hej.getFnamn(1234));


    }
}
