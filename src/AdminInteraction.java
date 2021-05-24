import com.sun.security.jgss.GSSUtil;

import java.sql.SQLException;
import java.util.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class AdminInteraction {
    Getset object;

    public AdminInteraction(Getset obj){
        object = obj;
    }

    public boolean checkIfSuspended(int UserId) {
        HelpMethod HM = new HelpMethod(object);

        Date datum = HM.getAUser(UserId).getSuspendDate();
        System.out.println("Användarens datum: " + datum);
        java.util.Date dagensdatum = java.sql.Date.valueOf(LocalDate.now());
        System.out.println("Dagensdatum: " + dagensdatum);

        if (datum == null){
            System.out.println("Användaren är inte suspenderad!");
            return false;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(datum);
        c.add(Calendar.DATE, 15);

        System.out.println(HM.getAUser(UserId).getSuspendDate().compareTo(dagensdatum));

        if (HM.getAUser(UserId).getSuspendDate().compareTo(dagensdatum) >= 0) {
            HM.getAUser(UserId).setSuspendDate(null);

            try {
                object.resetSuspend(UserId);
            }catch (SQLException e) {
                System.out.println("Something went wrong with database connection");
            }

            System.out.println("Det har gått 15 dagar");
            return true;
        }

        System.out.println("Är suspenderad");
        return false;
    }

    public boolean suspendUser (int UserId){
        HelpMethod HM = new HelpMethod(object);
        User anvandare = HM.getAUser(UserId);

        if (anvandare.Delays <=2 || anvandare.Delays ==4 || anvandare.Delays ==5 || anvandare.Delays ==7 || anvandare.Delays ==8 ){
            System.out.println("Användare ska inte suspenderas");
            return false;
        }
        else if (HM.getAUser(UserId).Delays == 9){
            System.out.println("Radera denna användare");
            return false;
        }
        if(anvandare.getDelays() == 3 || anvandare.getDelays() == 6 ){
            HM.getAUser(UserId).setSuspendDate(java.sql.Date.valueOf(LocalDate.now()));

            try {
                HM.suspendUser(UserId, java.sql.Date.valueOf(LocalDate.now()));
            }catch (SQLException e) {
                System.out.println("Something went wrong with database connection");
            }
        }
        return true;
        //if a member delays to return library items more than twice,
        // he/she gets suspended for 15 days.
        // If he/she has been suspended more than twice, then the account is deleted.
    }

    public void checkIfUserCanLend(int ISBN, int userId)  { //inparametern blir en metod (requestForBook)
        HelpMethod HM = new HelpMethod(object);
        int nmrBooks = HM.getAllBooksOnISBN(ISBN).size();

        for(int i = 0; i < nmrBooks; i++){
            Book bok;
            bok = HM.getAllBooksOnISBN(ISBN).get(i);
            if(bok.getBorrowed() == null){
                System.out.println("Bok med detta ISBN på ID: " + bok.getId() + " är ledig och kommer att lånas ut till användare med iD " + userId);
                HM.addBookToAUser(userId, bok.getId());
                break;
            }
            else System.out.println("Bok med detta ISBN på ID: " + bok.getId() + " är tyvärr inte ledig och kommer INTE att lånas ut");
        }


        //Afterward, the librarian is looking for the library title with the ISBN (an integer of 6 digits, e.g., 238103):
        //• If it does not exist then the member is notified (i.e., you should print a message to the librarian who uses the system).

        //• If the library title is found, then the librarian checks if there is an available item for borrowing
        // and in case there is not an available item you notify the librarian (i.e., you should print a message to the librarian who uses the system).

        //• If there is an available item, then the item is given to the member,
        // and the system updates the number of the member’s borrowed items appropriately and the available items of this title.
    }

    /*
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

*/

    public static void main(String[] args) {
        //hej.RegisterUser("Martin", "Nilssn", 880528, 3);
        Getset obj = new Getset();
        AdminInteraction hoj = new AdminInteraction(obj);
        hoj.checkIfUserCanLend(987654, 1234);
    }
}
