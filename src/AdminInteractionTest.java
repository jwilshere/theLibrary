import org.junit.jupiter.api.Test;

import java.awt.desktop.UserSessionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AdminInteractionTest {


    @Test
    void suspendUser() throws SQLException{ //KLAR !!!!!!!!!!!!!!!
        Getset mock = mock(Getset.class);
        AdminInteraction AI = new AdminInteraction(mock);
        HelpMethod hoj = new HelpMethod(mock);

        //Skapar en Arraylist och lägger in en användare
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(3, "Marre", "Jonson", 980103, 3, 0,3,1,3, null));
        users.add(new User(4444, "Philip", "Lindquist", 980606, 3, 0,3,1,0, null));
        users.add(new User(6666, "Philip", "Lindquist", 980606, 3, 0,3,1,0, null));

        when(mock.getUsers())
                .thenReturn(users);

        System.out.println("Före delays är kollade: " + hoj.getAUser(3).SuspendDate);
        assertTrue(AI.suspendUser(3));
        System.out.println("Efter delays är kollade: " + hoj.getAUser(3).SuspendDate);
    }

    @Test
    void deleteUser() throws SQLException{ //KLAR !!!!!!!!!!!!!!!
        Getset mock = mock(Getset.class);
        HelpMethod HM = new HelpMethod(mock);

        //Skapar en Arraylist och lägger in en användare
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(3333, "Marre", "Jonson", 980103, 3, 0,3,1,0, null));
        users.add(new User(4444, "Philip", "Lindquist", 980606, 3, 0,3,1,0, null));

        when(mock.getUsers())
                .thenReturn(users);

        System.out.println("Före deleten: " + HM.getUsers());
        HM.deleteUser(3333);
        System.out.println("Efter deleten: " + HM.getUsers());

    }

    @Test
    void addABookToUser() throws SQLException{ //KLAR !!!!!!!!!!!!!!!
        Getset mock = mock(Getset.class);
        HelpMethod HM = new HelpMethod(mock);

        //Skapar två "book"-objekt, samma objekt som förs i den förväntade arrayen som förs in i användarens lånade.
        Book book1 = new Book(1, "Jogge", 22, null);
        Book book2 = new Book(2, "Joggelito", 33, null);

        //Skapar en array och lägger in de två "book"-objekten
        Book[] expectedBooks = new Book[10];
        expectedBooks[0] = book1;
        expectedBooks[1] = book2;

        //Skapar en Arraylist och lägger in en användare
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(4444, "jogel", "Jonson", 1111, 3, 0, 3, 1, 0, null));

        //Lägger till de två "book"-objekten i en Arraylist
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        //Mockar Användaren och Böckerna så att vi blir oberoende av databasen
        when(mock.getUsers())
                .thenReturn(users);

        when(mock.getBooks())
                .thenReturn(books);

        //Addar de två "book"-objekten till användaren
        HM.addBookToAUser(4444, 1);
        HM.addBookToAUser(4444, 2);

        //Testar så att vår förväntade array av användarens böcker är samma som den faktiska
        assertArrayEquals(expectedBooks, HM.getAUser(4444).getBookLista());
        System.out.println("Expected: " + Arrays.toString(expectedBooks) + " " + "Faktiska: " + Arrays.toString(HM.getAUser(4444).getBookLista()));
        System.out.println(HM.getAUser(4444).getItemsBorrowed());
    }

    @Test
    void getAUser() throws SQLException { //KLAR !!!!!!!!!!!!!!!
        Getset mock = mock(Getset.class);
        HelpMethod HM = new HelpMethod(mock);
        User anvandare = new User(1234, "Joel", "Jonson", 980603, 3, 0,3,1,0,null);

        ArrayList<User> hej = new ArrayList<>();
        hej.add(anvandare);

        when(mock.getUsers())
                .thenReturn(hej);

       assertEquals(anvandare, HM.getAUser(1234));
       System.out.println("Excpected: " + anvandare + " " + "Faktiska: " + HM.getAUser(1234));

    }

    @Test
    void checkIfUserCanLend() throws SQLException{ //KLAR !!!!!!!!!!!!!!!
        Getset mock = mock(Getset.class);
        AdminInteraction AI = new AdminInteraction(mock);
        HelpMethod HM = new HelpMethod(mock);

        //Skapar två "book"-objekt, samma objekt som förs i den förväntade arrayen som förs in i användarens lånade.
        Book book1 = new Book(1, "Jogge", 22, null);
        Book book2 = new Book(2, "Joggelito", 33, null);

        //Skapar en array och lägger in de två "book"-objekten
        Book[] expectedBooks = new Book[10];
        expectedBooks[0] = book1;
        expectedBooks[1] = book2;

        //Skapar en Arraylist och lägger in en användare
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(4444, "jogel", "Jonson", 1111, 3, 0, 3, 1, 0, null));

        //Lägger till de två "book"-objekten i en Arraylist
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        //Mockar Användaren och Böckerna så att vi blir oberoende av databasen
        when(mock.getUsers())
                .thenReturn(users);

        when(mock.getBooks())
                .thenReturn(books);

        AI.checkIfUserCanLend(22, 4444);
        AI.checkIfUserCanLend(33, 4444);

        //Testar så att vår förväntade array av användarens böcker är samma som den faktiska
        assertArrayEquals(expectedBooks, HM.getAUser(4444).getBookLista());
        System.out.println("Expected: " + Arrays.toString(expectedBooks) + "\nFaktiska: " + Arrays.toString(HM.getAUser(4444).getBookLista()));
    }

    @Test
    void requestBook() throws SQLException{ //KLAR !!!!!!!!!!!!!!!
        Getset mock = mock(Getset.class);
        HelpMethod HM = new HelpMethod(mock);

        Book book1 = new Book(1, "Äventyr", 22, null);
        Book book2 = new Book(2, "Trädgård", 33, null);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        ArrayList<User> users = new ArrayList<>();
        users.add(new User(4444, "jogel", "Jonson", 1111, 3, 2, 3, 1, 0, null));

        when(mock.getUsers())
                .thenReturn(users);

        when(mock.getBooks())
                .thenReturn(books);

        System.out.println("Önskad bok med ISBN: " + HM.requestBook("Äventyr", 4444) + " går att låna!");
        assertEquals(22, HM.requestBook("Äventyr", 4444));
    }
}