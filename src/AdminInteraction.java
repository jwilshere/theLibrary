import com.sun.security.jgss.GSSUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class AdminInteraction {
    private static Logger logger = LogManager.getLogger(AdminInteraction.class.getName());
    Getset object;

    public AdminInteraction(Getset obj){
        object = obj;
    }

    public boolean checkIfSuspended(int UserId) {
        logger.trace("---> checkIdSuspended");

        Getset hoj = new Getset();
        HelpMethod object = new HelpMethod(hoj);

        Date datum = object.getAUser(UserId).getSuspendDate();

        java.util.Date dagensdatum = java.sql.Date.valueOf(LocalDate.now());

        if (datum == null){
            System.out.println("Användaren är inte suspenderad!");
            logger.trace("<--- checkIdSuspended");
            return false;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(datum);
        c.add(Calendar.DATE, 15);
        if (c.getTime().compareTo(dagensdatum) < 0) {

            try {
                object.resetSuspend(UserId);
            }catch (SQLException e) {
                System.out.println("Something went wrong with database connection");
                logger.error("Kollade inte ifall användaren var suspenderad, metodfel!");
            }
            System.out.println("Det har gått 15 dagar, användaren är inte suspenderad längre");
            return true;
        }

        System.out.println("Är suspenderad");
        logger.trace("<--- checkIdSuspended");
        return false;
    }

    public boolean suspendUser (int UserId){
        logger.trace("---> suspendUser");

        HelpMethod HM = new HelpMethod(object);
        User anvandare = HM.getAUser(UserId);

        if (anvandare.Delays <=2 || anvandare.Delays ==4 || anvandare.Delays ==5 || anvandare.Delays ==7 || anvandare.Delays ==8 ){
            System.out.println("Användare ska inte suspenderas");
            logger.error("Användaren suspenderades inte pga för lite Delays");
            logger.trace("<--- suspendUser");
            return false;
        }
        else if (HM.getAUser(UserId).Delays == 9){
            System.out.println("Radera denna användare");
            logger.trace("<--- suspendUser");
            return false;
        }
        if(anvandare.getDelays() == 3 || anvandare.getDelays() == 6 ){
            HM.getAUser(UserId).setSuspendDate(java.sql.Date.valueOf(LocalDate.now()));

            try {
                HM.suspendUser(UserId, java.sql.Date.valueOf(LocalDate.now()));
                System.out.println("Användaren är härmed suspenderad!");
            }catch (SQLException e) {
                System.out.println("Something went wrong with database connection");
                logger.error("Användaren suspenderades inte, metodfel!");
            }
        }
        logger.trace("<--- suspendUser");
        return true;
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
    }

    public void ReturnBook (int userId, int bookId) {
        HelpMethod HM = new HelpMethod(object);
        Book[] bokLista = HM.getAUser(userId).getBookLista();
        Book[] newBokLista = new Book[10];
        int nmr = 0;

        int i = 0;
        int x = 0;
        while(i < bokLista.length){
            if(bokLista[i] == null){
                newBokLista[x] = bokLista[i];
                i++;
                x++;
            }
            else if(bokLista[i].getId() == bookId) {
                i++;
            }
            newBokLista[x] = bokLista[i];
            i++;
            x++;
        }
        HM.getAUser(userId).bookLista = newBokLista;

        try {
            object.removeBookFromUSer(bookId, userId);
        } catch (SQLException ex) {
            System.out.println("Something went wrong with database connection");
        }

    }

    public boolean RegisterUser(String Fnamn, String Lnamn, int PersonID, int Typ) { //Går inte att testa pga random UserID
        logger.trace("---> RegisterUser");

        object = new Getset();
        HelpMethod HM = new HelpMethod(object);

        int Id = HM.generateUserId();

        for (User s : HM.getUsers()) {
            if (s.getPersonId() == PersonID) {
                System.out.println("Användare är redan registrerad!");
                return false;
            }
        }
        try {
            object.addUser(Id, Fnamn, Lnamn, PersonID, Typ, 0, Typ, 1, 0, null);
        } catch (SQLException ex) {
            System.out.println("Something went wrong with database connection");
            logger.error("Användaren lades inte till, metodfel!");
        }
        logger.trace("<--- RegisterUser");
            return true;
    }

    public static void main(String[] args) {
        Getset obj = new Getset();
        AdminInteraction hoj = new AdminInteraction(obj);
        hoj.ReturnBook(1234, 1234);
    }
}
