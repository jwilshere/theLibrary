import java.sql.Date;

public class AdminInteraction {

    public void addUser(User User){
        //select statement som kollar ifall
    }

    public void deleteUser(){
    }

    public void checkIfUserDelayed(int id){

    }

    public void checkIfBookIsAvaible(int ISBN)  { //inparametern blir en metod (requestForBook)

        //Afterward, the librarian is looking for the library title with the ISBN (an integer of 6 digits, e.g., 238103):
        //• If it does not exist then the member is notified (i.e., you should print a message to the librarian who uses the system).

        //• If the library title is found, then the librarian checks if there is an available item for borrowing
        // and in case there is not an available item you notify the librarian (i.e., you should print a message to the librarian who uses the system).

        //• If there is an available item, then the item is given to the member,
        // and the system updates the number of the member’s borrowed items appropriately and the available items of this title.
    }

    public void ReturnBook (int PersonID, int bookId){
        //Kolla ifall usern finns, ta bort antal lånade items på usern
        //Uppdatera i BookRegister att en bok av denna titel är tillgänglig
    }
}
