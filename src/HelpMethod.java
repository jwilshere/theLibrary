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

    ArrayList<User> userLista = new ArrayList<>();
    ArrayList<Book> bookLista = new ArrayList<>();

    public User[] getUsers() {
    object = new Getset();
    int nummer = 0;
    User[] usLista = new User[object.getUsers().length];
    for(User s: object.getUsers()){
        usLista[nummer] = s;
        nummer++;
    }
    return usLista;
    }

    public Book[] getBooks() {
        object = new Getset();
        int nummer = 0;
        Book[] boLista = new Book[object.getBooks().length];
        for(Book s: object.getBooks()){
            boLista[nummer] = s;
            nummer++;
        }
        return boLista;
    }

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

    public void addBookToUser(int userId, int bookId) {
                for(Book p: getBooks()){
                    if(p.getId() == bookId){
                        getAUserOnId(userId).addBook(p);
                    }
                }
            }

    public User getAUserOnId(int userId){
        anvandare = new User();
        for(User s: userLista) {
            if (s.getId() == userId) {
                anvandare = s;
            }
        }
        return anvandare;
    }

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

    public boolean checkIfSuspended(int UserId) {
        object = new Getset();
        if (getAUser(UserId).getSuspendDate() == null){
            return false;
        }
        Date datum = getAUser(UserId).getSuspendDate();
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

    public int requestBook(String title, int userId) {
        int ISBN = 0;

        

        //Then, the system checks whether this member is an undergraduate,
        // a postgraduate, a PhD student/candidate, or a teacher (professor, etc.).
        // The number of library items that he/she has borrowed in the past ?????? SKITA I DETTA?
        // is being checked and then whether he or she has the permission to borrow a new one according
        // to the limitation applicable in each case (as described in the paragraph above).

        //FÖRST KOLLA IFALL DEN FÅR LÅNA; IFALL DEN FÅR SÅ RETURNERA TITELNAMNET
        return ISBN;
    }

    public static void main(String[] args) {
      /*  HelpMethod hej = new HelpMethod();
        System.out.println(hej.getAUserOnId(1234));
        System.out.println(hej.getABookOnId(4444));


        hej.userLista.add(new User(12, "Philip", "Nilsson", 9802, 1, 0,1,1,1,null));

        System.out.println("Detta är användare: " + Arrays.toString(hej.getUsers()));
        System.out.println("Detta är böckerna: " + Arrays.toString(hej.getBooks()));
        //hej.addBookToUser(1234, 12);
        System.out.println("Detta är en användares böcker: " + Arrays.toString(hej.getAUserOnId(1234).getBookLista()));
        System.out.println(hej.generateUserId());

       */

        System.out.println(java.time.LocalDate.now());
    }
}
