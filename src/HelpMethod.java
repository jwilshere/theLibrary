import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class HelpMethod extends Getset{
    User anvandare;
    Book bok;

    ArrayList<User> userLista = new ArrayList<>();
    ArrayList<Book> bookLista = new ArrayList<>();

    @Override
    public User[] getUsers() {
        User[] UsLista = new User[0];
        int count = 0;
        for(User s: userLista){
            UsLista[count] = s;
            count++;
        }
        return UsLista;
    }

    @Override
    public Book[] getBooks() {
        Book[] BoLista = new Book[bookLista.size()];
                bookLista.toArray(BoLista);
        return BoLista;
    }

    @Override
    public void addBookToUser(int userId, int bookId) {
                for(Book p: getBooks()){
                    if(p.getId() == bookId){
                        getAUser(userId).addBook(p);
                    }
                }
            }

    public User getAUser(int UserId){
        anvandare = new User();
        for(User s: userLista) {
            if (s.getId() == UserId) {
                anvandare = s;
            }
        }
        return anvandare;
    }

    public Book getABookOnId(int bookId){
        bok = new Book();
        for(Book s: bookLista) {
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
        System.out.println(hej.getAUser(1234));
        System.out.println(hej.getABookOnId(987654));

        hej.bookLista.add(new Book(12, "Tja", 1234, null));
        hej.bookLista.add(new Book(13, "Tja", 1235, null));
        hej.userLista.add(new User(1234, "Philip", "Nilsson", 9802, 1, 0,1,1,1,null));

        System.out.println(Arrays.toString(hej.getBooks()));
        hej.addBookToUser(1234, 12);
        System.out.println(Arrays.toString(hej.getAUser(1234).getBookLista()));
    }
}
