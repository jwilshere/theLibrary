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


    public boolean checkIfSuspended(int UserId) {
        if (getAUser(UserId).suspendDate == null){
            return false;
        }

        Date datum = getAUser(UserId).getSuspendDate();
        java.util.Date dagensdatum = java.sql.Date.valueOf(LocalDate.now());

            Calendar c = Calendar.getInstance();
            c.setTime(datum);
            c.add(Calendar.DATE, 1);
            if (c.getTime().compareTo(dagensdatum) < 0) {
                System.out.println("Det har gått 15 dagar");
                return true;
            }

            System.out.println("Är suspenderad");
        return false;
    }

    public static void main(String[] args) {
        HelpMethod hej = new HelpMethod();
        System.out.println(LocalDate.now());
        System.out.println(hej.checkIfSuspended(1));
        System.out.println(hej.getAUser(1234));
        System.out.println(hej.getABookOnId(987654));



        //hej.addBookToUser(1234, 4444);
        //hej.removeBookFromUSer(4444);
    }
}
