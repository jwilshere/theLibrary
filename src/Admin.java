import java.sql.*;

public class Admin {
    int id;

    User hej = new User();

    public void addUser(User User){
        //select statement som kollar ifall
    }

    public void deleteUser(){
    }

    public void checkIfUserDelayed(int id){

    }

    public void suspendUser (Date startDate, Date endDate, int id){
        //if a member delays to return library items more than twice,
        // he/she gets suspended for 15 days.
        // If he/she has been suspended more than twice, then the account is deleted.
    }

    public void checkIfBookIsAvaible(int ISBN)  { //inparametern blir en metod (requestForBook)

        //Afterward, the librarian is looking for the library title with the ISBN (an integer of 6 digits, e.g., 238103):
        //• If it does not exist then the member is notified (i.e., you should print a message to the librarian who uses the system).

        //• If the library title is found, then the librarian checks if there is an available item for borrowing
        // and in case there is not an available item you notify the librarian (i.e., you should print a message to the librarian who uses the system).

        //• If there is an available item, then the item is given to the member,
        // and the system updates the number of the member’s borrowed items appropriately and the available items of this title.
    }

    public void ReturnBook (int PersonID, int ISBN){
        //Kolla ifall usern finns, ta bort antal lånade items på usern
        //Uppdatera i BookRegister att en bok av denna titel är tillgänglig
    }


}
