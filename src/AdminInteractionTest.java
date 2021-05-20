import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


import org.junit.jupiter.api.Test;

import java.awt.desktop.UserSessionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
        }

        @Test
        void checkIfSuspended() throws SQLException{
            Getset mock = mock(Getset.class);
            HelpMethod AI = new AdminInteraction(mock);
            HelpMethod hejda = new HelpMethod(mock);

            ArrayList<User> hej = new ArrayList<>();
            hej.add(new User(4444, "Flaska", "Jonson", 980603, 2, 0,3,1,0, new Date(1990, 4,18)));

            when(mock.getUsers())
                    .thenReturn(hej);

            assertFalse(AI.checkIfSuspended(4444));
            System.out.println(hejda.getAUser(4444).getSuspendDate());
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

}
