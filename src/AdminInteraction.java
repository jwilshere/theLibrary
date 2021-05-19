import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class AdminInteraction {
    HelpMethod object = null;
    Getset getSet = null;

    AdminInteraction(){

    }

    AdminInteraction(Getset HM){
        getSet = HM;
    }


    public void deleteUser(int userId){
        getSet = new Getset();
        int nummer = object.getAUser(userId).getId();

        if (nummer == userId) {
            object.deleteUser(userId);
        }
        else {
            System.out.println("User dosen't exist");
        }
    }

    public boolean checkIfSuspended(int UserId) {
        getSet = new Getset();
        if (object.getAUser(UserId).getSuspendDate() == null){
            System.out.println("Användaren är inte suspenderad!");
            return false;
        }
        java.util.Date datum = object.getAUser(UserId).getSuspendDate();
        java.util.Date dagensdatum = java.sql.Date.valueOf(LocalDate.now());

        Calendar c = Calendar.getInstance();
        c.setTime(datum);
        c.add(Calendar.DATE, 1);
        if (c.getTime().compareTo(dagensdatum) < 0) {
            object.resetSuspend(UserId);
            System.out.println("Det har gått 15 dagar");
            return true;
        }

        System.out.println("Är suspenderad");
        return false;
    }

    public boolean suspendUser (int UserId){
        getSet = new Getset();

        int antaldelays =0;
        if (object.getAUser(UserId).Delays <=2 || object.getAUser(UserId).Delays ==4 || object.getAUser(UserId).Delays ==5 || object.getAUser(UserId).Delays ==7 || object.getAUser(UserId).Delays ==8 ){
            return false;
        }
        if (object.getAUser(UserId).Delays ==9){
            // kalla på radera användaren metoden
            return false;
        }
        if(object.getAUser(UserId).Delays >2){
            object.getAUser(UserId).setSuspendDate(java.sql.Date.valueOf(LocalDate.now()));
            getSet.suspendUser(UserId,java.sql.Date.valueOf(LocalDate.now()));
            antaldelays = object.getAUser(UserId).Delays +1;
            object.setDelays(UserId,antaldelays);
            //getAUser(UserId).setDelays(+1);
        }
        return true;
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

    public void ReturnBook (int userId, int bookId) {
        getSet = new Getset();
        object = new HelpMethod();

        int lanadeBocker;
        int nummer = object.getAUser(userId).getId();

        if (nummer == userId) {
            lanadeBocker = object.getAUser(userId).getItemBorrowed();
            lanadeBocker = lanadeBocker - 1;
            getSet.removeBookFromUSer(bookId);
            getSet.updateItemBorrowed(lanadeBocker, userId);
        }

        else {
            System.out.println("User dosen't exist");
        }
    }

    public void RegisterUser(String Fnamn, String Lnamn, int PersonID, int Typ){
        getSet = new Getset();
        object = new HelpMethod();

        for(User s: object.getUsers()){
            if(s.getPersonId() == PersonID){
                System.out.println("Användare är redan registrerad!");
            }
            if(s.getSuspendDate() != null){
                System.out.println("Användare är suspenderad");
            }
        }
        int Id = object.generateUserId();
        getSet.setUser(Id, Fnamn, Lnamn, PersonID, Typ, 0, Typ, 1,0, null);
    }

    public static void main(String[] args) {
        AdminInteraction hej = new AdminInteraction();
        hej.RegisterUser("Martin", "Nilssn", 880528, 3);

        hej.checkIfSuspended(1234);
    }

}
