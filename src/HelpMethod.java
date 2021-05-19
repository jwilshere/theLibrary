import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class HelpMethod {
    Getset object;
    User anvandare;
    Book bok;

    public User getAUser(int UserId){
        object = new Getset();
        anvandare = new User();

        for(User s: object.getUsers()) {
            if (s.getId() == UserId) {
                anvandare = s;
            }
        }
        return anvandare;
    }

    public Book getABookOnId(int bookId){
        object = new Getset();
        bok = new Book();

        for(Book s: object.getBooks()) {
            if (s.getId() == bookId) {
                bok = s;
            }
        }
        return bok;
    }
    public boolean suspendUser (int UserId){
        object = new Getset();
        int antaldelays =0;
        if (getAUser(UserId).Delays <=2 || getAUser(UserId).Delays ==4 ||getAUser(UserId).Delays ==5 ||getAUser(UserId).Delays ==7 ||getAUser(UserId).Delays ==8 ){
            return false;
        }
        if (getAUser(UserId).Delays ==9){
            // kalla på radera användaren metoden
            return false;
        }
        if(getAUser(UserId).Delays >2){
            getAUser(UserId).setSuspendDate(java.sql.Date.valueOf(LocalDate.now()));
            object.suspendUser(UserId,java.sql.Date.valueOf(LocalDate.now()));
            antaldelays = getAUser(UserId).Delays +1;
            object.setDelays(UserId,antaldelays);
            //getAUser(UserId).setDelays(+1);
        }
        return true;
        //if a member delays to return library items more than twice,
        // he/she gets suspended for 15 days.
        // If he/she has been suspended more than twice, then the account is deleted.
    }

    public boolean checkIfSuspended(int UserId) {
        object = new Getset();
        if (getAUser(UserId).suspendDate == null){
            return false;
        }

        Date datum = getAUser(UserId).getSuspendDate();
        java.util.Date dagensdatum = java.sql.Date.valueOf(LocalDate.now());

            Calendar c = Calendar.getInstance();
            c.setTime(datum);
            c.add(Calendar.DATE, 15);
            if (c.getTime().compareTo(dagensdatum) < 0) {
                object.resetSuspend(UserId);
                System.out.println("Det har gått 15 dagar");
                return true;
            }

            System.out.println("Är suspenderad");
        return false;
    }

    public static void main(String[] args) {
        HelpMethod hej = new HelpMethod();
        System.out.println(hej.suspendUser(1234));
        System.out.println(hej.checkIfSuspended(1234));
        System.out.println(hej.getAUser(1234));
        System.out.println(hej.getABookOnId(987654));



        //hej.addBookToUser(1234, 4444);
        //hej.removeBookFromUSer(4444);
    }
}
