import java.sql.*;

public class Admin {
    int id;

    User hej = new User();

    public void addUser(User User){
        //select statement som kollar ifall

    }

    public void deleteUser(){

    }

    public void canUserBorrow(int id){
        //Jämföra id:t med databas idn för att se att den finns
        //Om den finns så kolla vilken type det är och vad borrowlimiten ligger på


        try(Connection conn = DriverManager.getConnection(
                "jdbc:mysql://@vhost6.lnu.se:23306/1ik013v20-20?useSSL=false",
                "1ik013v20-20", "kiwi")) {
            System.out.println("Connected");

            System.out.println();
            System.out.println("---Album---");

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery("Select * FROM album");

            while(result.next()){
                System.out.println(result.getString(1) + ": " + result.getString(2) + ": " + result.getString(3));
            }

            System.out.println();
            System.out.println("---Artister---");

            Statement statement1 = conn.createStatement();

            ResultSet result1 = statement1.executeQuery("Select * FROM artist");

            while(result1.next()){
                System.out.println(result1.getString(1) + ": " + result1.getString(2) + ": " + result1.getString(3));
            }

        }
        catch(SQLException ex){
            System.out.println("Something went wrong");
        }

    }

    public void martindabosss(){

    }


}
