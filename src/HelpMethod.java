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

    public void addBookToAUser(int userId, int bookId) {
                for(Book p: getBooks()) {
                    if (p.getId() == bookId) {
                        getAUser(userId).addBook(p);
                        getAUser(userId).setItemsBorrowed(getAUser(userId).getItemsBorrowed() + 1);
                        try {
                            object.addBookToUser(userId, bookId, getAUser(userId).getItemsBorrowed() + 1, java.sql.Date.valueOf(LocalDate.now()));
                        }catch (SQLException e) {
                            System.out.println("Something went wrong with database connection");
                        }
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

        if (nummer == userId) {
            int index = getUsers().indexOf(user);
            getUsers().remove(index);

        //ifall userId stämmer så hämtas indexet som användaren ligger på i arrayn, sedan tags denna bort med remove
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
        int userType;
        userType = getAUser(userId).getType();
        User anvandare = getAUser(userId);

        switch (userType) {
            case 3:
                if (userType == 3) {
                    if(anvandare.getItemsBorrowed() >= 3){
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
                    if(anvandare.getItemsBorrowed() >= 5){
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
                    if(anvandare.getItemsBorrowed() >= 8){
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
                    if(anvandare.getItemsBorrowed() >= 10){
                        System.out.print("Denna användare har lånat max antal böcker, kan lånda: ");
                        break;
                    }
                    else {
                        ISBN = getISBNOnTitle(title);
                        break;
                    }
                }
        }
        return ISBN;
    }

    public ArrayList<Book> getDelayedBooks(){
        ArrayList<Book> delayedBooks = new ArrayList<>();

        java.util.Date dagensDatum = java.sql.Date.valueOf(LocalDate.now());

        for(Book s: getBooks()){
            if(s.getBorrowed() != null){
                Calendar c = Calendar.getInstance();
                c.setTime(s.getBorrowed());
                c.add(Calendar.DATE, 15);
                if(c.getTime().compareTo(dagensDatum) < 0){
                    delayedBooks.add(s);
                }
            }
        }
        return delayedBooks;
    }

    public boolean updateDelays (int UserId){
        HelpMethod HM = new HelpMethod(object);
        int antaldelays;
        antaldelays = getAUser(UserId).Delays +1;

        try {
            HM.setDelays(UserId, antaldelays);
            System.out.println("Delays uppdaterades på användaren!");
        }catch (SQLException ex) {
            System.out.println("Something went wrong with database connection");

        }
        return true;
    }

    public static void main(String[] args) {
        Getset hejda = new Getset();
        HelpMethod hej = new HelpMethod(hejda);

        for(Book s: hej.getDelayedBooks()){
            System.out.println(s.getId());
        }

    }
}
