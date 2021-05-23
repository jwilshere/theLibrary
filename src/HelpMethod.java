import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class HelpMethod extends Getset{
    User anvandare;
    Book bok;
    Getset object;

    public HelpMethod(Getset obj){
        object = obj;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> userLista = new ArrayList<>();
        try {
            userLista = object.getUsers();
        }catch (SQLException e) {
            System.out.println("Something went wrong with database connection");
        }
        return userLista;
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> bookLista = new ArrayList<>();
        try {
            bookLista = object.getBooks();
        }catch (SQLException e) {
            System.out.println("Something went wrong with database connection");
        }
        return bookLista;
    }

    public User getAUser(int UserId){
        anvandare = new User();

        for(User s: getUsers()) {
            if (s.getId() == UserId) {
                anvandare = s;
            }
        }
        return anvandare;
    }

    public void addBookToUser(int userId, int bookId) {
        for(Book p: getBooks()){
            if(p.getId() == bookId){
                getAUser(userId).addBook(p);
            }
        }
    }

   /* public boolean updateDelays (int UserId){
        HelpMethod HM = new HelpMethod(object);
        int antaldelays = 0;
        User anvandare = HM.getAUser(UserId);
        antaldelays = getAUser(UserId).Delays +1;
       // getAUser(UserId).setDelays(antaldelays);
        //getAUser(UserId).setDelays(+1);
      //  HM.getAUser(UserId).setDelays());
        try {
            HM.setDelays(UserId, antaldelays);
        }catch (SQLException ex) {
            System.out.println("Something went wrong with database connection");

        }
        return true;
        //if a member delays to return library items more than twice,
        // he/she gets suspended for 15 days.
        // If he/she has been suspended more than twice, then the account is deleted.
    }*/


    public boolean removesuspend (int UserId){
        HelpMethod HM = new HelpMethod(object);
        User anvandare = HM.getAUser(UserId);

        HM.getAUser(UserId).setSuspendDate(null);

        try {
            HM.suspendUser(UserId, null);
        }catch (SQLException ex) {
            System.out.println("Something went wrong with database connection");

        }
        return true;
        //if a member delays to return library items more than twice,
        // he/she gets suspended for 15 days.
        // If he/she has been suspended more than twice, then the account is deleted.
    }

           /*

    public User getAUserOnPersonId(int personId){
        anvandare = new User();
        for(User s: userLista) {
            if (s.getPersonId() == personId) {
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

    public int generateUserId(){
        Random random = new Random();
        int randomUserID = random.nextInt(8999) + 1000;

        for(User s: getUsers()){
            if(s.getId() == randomUserID){
                generateUserId();
            }
        }
        return randomUserID;
    }


    public int requestBook(String title, int userId) {
        int ISBN = 0;

        //Then, the system checks whether this member is an undergraduate,
        // a postgraduate, a PhD student/candidate, or a teacher (professor, etc.).
        // The number of library items that he/she has borrowed in the past ?????? SKITA I DETTA?
        // is being checked and then whether he or she has the permission to borrow a new one according
        // to the limitation applicable in each case (as described in the paragraph above).

        //FÖRST KOLLA IFALL DEN FÅR LÅNA; IFALL DEN FÅR SÅ RETURNERA TITELNAMNET
        return ISBN;
    } */

    public static void main(String[] args) {
        Getset hejda = new Getset();
        HelpMethod hej = new HelpMethod(hejda);
       /* System.out.println(hej.getAUserOnId(1234));
        System.out.println(hej.getABookOnId(4444));

        hej.bookLista.add(new Book(12, "Tja", 1234, null));
        hej.bookLista.add(new Book(13, "Tja", 1235, null));
        hej.userLista.add(new User(12, "Philip", "Nilsson", 9802, 1, 0,1,1,1,null));

        System.out.println("Detta är användare: " + Arrays.toString(hej.getUsers()));
        System.out.println("Detta är böckerna: " + Arrays.toString(hej.getBooks()));
        //hej.addBookToUser(1234, 12);
        System.out.println("Detta är en användares böcker: " + Arrays.toString(hej.getAUserOnId(1234).getBookLista()));
        System.out.println(hej.generateUserId());
        System.out.println(hej.getAUserOnId(1));*/

      //  System.out.println(hej.getUsers());
      //  System.out.println(hej.getAUser(1234));
        //System.out.println(hej.updateDelays(666));

    }
}
