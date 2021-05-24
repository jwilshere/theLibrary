import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminInteractionTest {

        @Test
        void addUser() {

        }

        @Test
        void deleteUser() {
        }


    @Test
    void suspendUser() throws SQLException{ //KLAR !!!!!!!!!!!!!!!
        Getset mock = mock(Getset.class);
        AdminInteraction AI = new AdminInteraction(mock);
        HelpMethod hoj = new HelpMethod(mock);

        //Skapar en Arraylist och lägger in en användare
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(3, "Marre", "Jonson", 980103, 3, 0,3,1,3, null));
        users.add(new User(4444, "Philip", "Lindquist", 980606, 3, 0,3,1,0, null));
        users.add(new User(6666, "Philip", "Lindquist", 980606, 3, 0,3,1,3, null));

        when(mock.getUsers())
                .thenReturn(users);
        
        System.out.println("Före delays är kollade: " + hoj.getAUser(3).SuspendDate);
        assertTrue(AI.suspendUser(3));
        System.out.println("Efter delays är kollade: " + hoj.getAUser(3).SuspendDate);
    }

        @Test
        void addABookToUser() throws SQLException {
            Getset mock = mock(Getset.class);
            HelpMethod HM = new HelpMethod(mock);

            //Skapar två "book"-objekt, samma objekt som förs i den förväntade arrayen som förs in i användarens lånade.
            Book book1 = new Book(12, "Hoj", 1111, null);
            Book book2 = new Book(13, "Hoj", 1111, null);

            Book[] excpetedBooks = new Book[10];
            excpetedBooks[0] = book1;
            excpetedBooks[1] = book2;

            ArrayList<User> users = new ArrayList<>();
            users.add(new User(4444, "Flaska", "Jonson", 980603, 3, 0,3,1,0, null));

            ArrayList<Book> books = new ArrayList<>();
            books.add(book1);
            books.add(book2);

            when(mock.getUsers())
                    .thenReturn(users);

            when(mock.getBooks())
                    .thenReturn(books);

            HM.addBookToUser(4444, 12);
            HM.addBookToUser(4444, 13);

            assertArrayEquals(excpetedBooks, HM.getAUser(4444).getBookLista());
            System.out.println(Arrays.toString(HM.getAUser(4444).getBookLista()));
        }

    @Test
    void updateDelays() throws SQLException{
        Getset mock = mock(Getset.class);
        AdminInteraction AI = new AdminInteraction(mock);
        HelpMethod hejda = new HelpMethod(mock);

        ArrayList<User> hej = new ArrayList<>();
        hej.add(new User(1, "Flaska", "Jonson", 980603, 2, 0,3,1,2, new Date(2021,04,17)));
        when(mock.getUsers())
                .thenReturn(hej);
        System.out.println(hejda.getAUser(1).getDelays());
        assertTrue(AI.updateDelays(1));
        System.out.println(hejda.getAUser(1).getDelays());
    }

      @Test
        void checkIfSuspended() throws SQLException{
            Getset mock = mock(Getset.class);
            AdminInteraction AI = new AdminInteraction(mock);
            HelpMethod hejda = new HelpMethod(mock);

            ArrayList<User> hej = new ArrayList<>();
            hej.add(new User(1, "Flaska", "Jonson", 980603, 2, 0,3,1,0, new Date(2021,04,17)));

            when(mock.getUsers())
                    .thenReturn(hej);

            assertTrue(AI.checkIfSuspended(1));
        }


        @Test
        void getAUser() throws SQLException {
            Getset mock = mock(Getset.class);
            HelpMethod HM = new HelpMethod(mock);
            User expected = new User(1234, "Joel", "Jonson", 980603, 3, 0,3,1,0,null);

            ArrayList<User> hej = new ArrayList<>();
            hej.add(new User(1234, "Joel", "Jonson", 980603, 3, 0,3,1,0,null));

            when(mock.getUsers())
                    .thenReturn(hej);

            assertEquals(expected.getId(), HM.getAUser(1234).getId());

        }

    @Test
    void RegisterUser() throws SQLException{
        Getset mock = mock(Getset.class);
        AdminInteraction AI = new AdminInteraction(mock);
        HelpMethod hejda = new HelpMethod(mock);

        ArrayList<User> hej = new ArrayList<>();
        hej.add(new User(1, "Flaska", "Jonson", 910403, 3, 0,3,1,0, null));
        hej.add(new User(3, "TJena", "Jonson", 950302, 3, 0,3,1,0, null));

        when(mock.getUsers())
                .thenReturn(hej);


        for(User s: hej){
            System.out.println(s.getId());
        }

        AI.RegisterUser("Philip", "Nilsson",0000,3);

        System.out.println();
        for(User s: hej){
            System.out.println(s.getId());
        }
        System.out.println(hejda.getAUserOnPersonId(910403));
    }
    @Test
    void checkIfUserCanLend() throws SQLException{
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




   /* @Test
    void checkIfSuspended() {
        Getset mock = mock(Getset.class);
        AdminInteraction AI = new AdminInteraction(mock);

        Boolean expected = false;

        when(mock.getUsers())
                .thenReturn(new User[] {new User(1234, "Joel", "Jonson", 980603, 3, 0,3,1,0,null)});

        assertEquals(expected, AI.checkIfSuspended(1234));

    }*/
    }


