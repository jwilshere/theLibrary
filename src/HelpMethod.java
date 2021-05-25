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

    public ArrayList<Book> getDelayBooks() {
        /*
        ArrayList<Book> senaBocker = new ArrayList<>();

        java.util.Date dagensdatum = java.sql.Date.valueOf(LocalDate.now());
        Date da = bok.getBorrowed();
        for (Book b: getBooks()) {
            if (da.compareTo(dagensdatum))
        }


        System.out.println("Dagensdatum: " + dagensdatum);


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

         */
        return null;
    }

    public boolean updateDelays (int UserId) {
        HelpMethod HM = new HelpMethod(object);
        int antaldelays = 0;
        User anvandare = HM.getAUser(UserId);
        antaldelays = getAUser(UserId).Delays + 1;
        try {
            if (HM.getAUser(UserId).getId() == UserId) {
                HM.setDelays(UserId, antaldelays);
                System.out.println("Delay är uppdaterad på användaren med ID: " + UserId);
            }
            else {
                System.out.println("Användaren finns ej"); }

        } catch (SQLException ex) {
            System.out.println("Something went wrong with database connection");

        }
        return true;
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

    public void addBookToAUser(int userId, int bookId) {
                for(Book p: getBooks()){
                    if(p.getId() == bookId){

                       try {
                           for(Book s: object.getBooksBorrowedByUser(userId)){
                               getAUser(userId).addBook(s);
                           }
                           object.addBookToUser(userId, bookId);
                           object.updateItemBorrowed(getAUser(userId).getItemBorrowed() + 1, userId);
                        }catch (SQLException e) {
                            System.out.println("Something went wrong with database connection");
                        }
                        getAUser(userId).addBook(p);
                    }
                }
            }

    public void deleteUser(int userId){
        int nummer = getAUser(userId).getId();
        User user = getAUser(userId);

        try {
            object.deleteUser(userId);
        }catch (SQLException e) {
            System.out.println("Something went wrong with database connection");
        }

        //ifall userId stämmer så hämtas indexet som användaren ligger på i arrayn, sedan tags denna bort med remove
        if (nummer == userId) {
           int index = getUsers().indexOf(user);
           getUsers().remove(index);
        }
        else {
            System.out.println("User dosen't exist");
        }


    }

    public User getAUserOnPersonId(int personId){
        anvandare = new User();

        for(User s: getUsers()) {
            if (s.getPersonId() == personId) {
                anvandare = s;
            }
        }
        return anvandare;
    }

    public ArrayList<Book> getAllBooksOnISBN(int bookISBN){
        bok = new Book();
        ArrayList<Book> books = new ArrayList<>();

        for(Book s: getBooks()) {
            if (s.getISBN() == bookISBN) {
                books.add(s);
            }
        }
        return books;
    }

    public int getISBNOnTitle (String bookTitle){
        bok = new Book();

        for(Book s: getBooks()) {
            if (s.getTitle().equals(bookTitle)) {
                bok = s;
            }
        }

        return bok.getISBN();
    }

    /*
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

*/
    public int requestBook(String title, int userId) {
        int ISBN = 0;
        int userType;
        userType = getAUser(userId).getType();
        User anvandare = getAUser(userId);

        switch (userType) {
            case 3:
                if (userType == 3) {
                    if(anvandare.getItemBorrowed() >= 3){
                        System.out.print("Denna användare har lånat max antal böcker, kan låna: ");
                        break;
                    }
                    else {
                        ISBN = getISBNOnTitle(title);
                        break;
                    }
                }

            case 5:
                if (userType == 5) {
                    if(anvandare.getItemBorrowed() >= 5){
                        System.out.print("Denna användare har lånat max antal böcker, kan låna: ");
                        break;
                    }
                    else {
                        ISBN = getISBNOnTitle(title);
                        break;
                    }
                }

            case 8:
                if (userType == 8) {
                    if(anvandare.getItemBorrowed() >= 8){
                        System.out.println("Denna användare har lånat max antal böcker, kan låna: ");
                        break;
                    }
                    else {
                        ISBN = getISBNOnTitle(title);
                        break;
                    }
                }

            case 10:
                if (userType == 10) {
                    if(anvandare.getItemBorrowed() >= 10){
                        System.out.print("Denna användare har lånat max antal böcker, kan lånda: ");
                        break;
                    }
                    else {
                        ISBN = getISBNOnTitle(title);
                        break;
                    }
                }
        }

        //Then, the system checks whether this member is an undergraduate,
        // a postgraduate, a PhD student/candidate, or a teacher (professor, etc.).
        // The number of library items that he/she has borrowed in the past ?????? SKITA I DETTA?
        // is being checked and then whether he or she has the permission to borrow a new one according
        // to the limitation applicable in each case (as described in the paragraph above).

        //FÖRST KOLLA IFALL DEN FÅR LÅNA; IFALL DEN FÅR SÅ RETURNERA TITELNAMNET
        return ISBN;
    }

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
        //System.out.println(hej.getAUserOnPersonId(980603).getFnamn());
        System.out.println(hej.requestBook("Jonson's Äventyr", 1234));

    }
}
