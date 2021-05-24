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
        hej.addBookToAUser(1234, 1235);

    }
}
