import java.sql.*;

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

        System.out.println(hej.getFnamn(1234));


    }
}
